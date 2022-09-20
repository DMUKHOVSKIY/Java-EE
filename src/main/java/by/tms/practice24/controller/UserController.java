package by.tms.practice24.controller;


import by.tms.practice24.dto.UserDataDTO;
import by.tms.practice24.dto.UserResponseDTO;
import by.tms.practice24.entity.AppUser;
import by.tms.practice24.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signin")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDataDTO user) { //Мы используем UserDataDTO, так как у entity AppUser есть поле id и вообще это не совсем хороший подход
        return userService.signup(modelMapper.map(user, AppUser.class)); //конвертируем UserDataDTO в AppUser
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //если есть роли и на этот метод приходит запрос
    // и в SecurityContext сидит пользователь, у которого роль не ROLE_ADMIN, а другая, то ему доступ
    // будет запрещен
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponseDTO search(@PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public UserResponseDTO whoami(HttpServletRequest req) { //возвращаем UserResponseDTO, так как там нет пароля, а у обычного AppUser есть пароль
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class); //конвертируем AppUser в UserResponseDTO
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }
}
