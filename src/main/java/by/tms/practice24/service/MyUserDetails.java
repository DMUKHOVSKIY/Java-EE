package by.tms.practice24.service;

import by.tms.practice24.dao.UserRepository;
import by.tms.practice24.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService { //работает благодаря AuthenticationManager в SecurityConfiguration

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //получаем детали для UsernamePasswordAuthenticationToken. Этот метод вызывается в TokenProvider
        final AppUser appUser = userRepository.findByUsername(username); //берем нашего пользователя по username

        if (appUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return User //формируем user-а, который идет из ""import org.springframework.security.core.userdetails.User;" и возращать его как детали
                .withUsername(username)//
                .password(appUser.getPassword())
                .authorities(appUser.getAppUserRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
