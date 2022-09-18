package by.tms.practice23.controller;

import by.tms.practice23.entity.User;
import by.tms.practice23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    //PostMapping метод на логин делать не надо, так как он уже готов. Мы должны отдать для логина только нашу кастомную форму

    @GetMapping("/reg")
    public String reg(){
        return "reg";
    }

    @PostMapping("/reg")
    public String reg(User user){
        userService.registration(user);
        return "redirect:/";
    }
}
