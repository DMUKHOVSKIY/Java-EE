package by.tms.practice24.service;

import by.tms.practice24.configuration.TokenProvider;
import by.tms.practice24.dao.UserRepository;
import by.tms.practice24.entity.AppUser;
import by.tms.practice24.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public String signin(String username, String password) { //логин
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); //создаем токен исходя из username and password
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles()); //создаем на основании его токен
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY); //если что произойдет ошибка
        }
    }

    public String signup(AppUser appUser) {//регистрация
        if (!userRepository.existsByUsername(appUser.getUsername())) { //проверка, есть ли такой пользователь по username
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword())); //кодируется пароль
            userRepository.save(appUser);//сохранение пользователя
            return jwtTokenProvider.createToken(appUser.getUsername(), appUser.getAppUserRoles()); //также создается токен, но это не обязательно
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public AppUser search(String username) {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return appUser;
    }

    public AppUser whoami(HttpServletRequest req) { //Очень часто этот метод нужен, чтобы мы могли отправить запрос с
        // клиента и просто получить токен либо сопутствующую информацию. Мы можем просто отправить токен и спросить: "Кто я такой?"
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) { //клиент каждый час обновляет токен
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
    }
}
