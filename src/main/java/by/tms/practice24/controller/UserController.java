package by.tms.practice24.controller;

import by.tms.practice24.configuration.TokenProvider;
import by.tms.practice24.dao.UserRepository;
import by.tms.practice24.entity.User;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
import by.tms.practice24.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User Description")
public class UserController {

    @Autowired
    private UserRepository userRepository; //вообще нужно загнать все в UserService

    @Autowired
    private TokenProvider tokenProvider;


    @PostMapping("/reg")
    public ResponseEntity<User> reg(@RequestBody @Valid User user) { //save() работает как регистрация в REST. Нет отдельного метода регистрация
        User save = userRepository.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(String username, String password) { //возвращаем типо токен
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if (user.getPassword().equals(password)) {
                String s = tokenProvider.generateToken(user.getUsername());//генерируем токен на username and password
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/id")
    public ResponseEntity<User> findById(long id) {
        Optional<User> byId = userRepository.findById(id);
        if (id == 555) {
            throw new RuntimeException("Bad ID"); //будет выводить вместо стэк трэйса с ненужной иноформацией "Bad ID", благодаря нашему классу ExController
        }
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody @Valid User user) {
        User save = userRepository.save(user);//Save() работает по такому же принципу, как и save() в Hibernate. То есть save() работает как save() и как  update()
        return ResponseEntity.ok(save); //save() понимает, что нужно обновить, а не сохранить если внутри user-а, который нам пришел есть id и этот id совпадает с id user-а в БД, то происходит обновление. Если id в БД - нет, либо он=0 - происходит сохранение
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid User user) { //Возвращать мы будем только код. Никаких данных в body у нас не будет. Мы не знаем что там будет <?>
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/id")
    public ResponseEntity<?> deleteById(long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
