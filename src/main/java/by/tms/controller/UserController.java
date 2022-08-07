package by.tms.controller;

import by.tms.entity.User;
import by.tms.service.UserService;
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

    @GetMapping("/registration")
    public String registration() {
        return "reg";
    }

    @PostMapping("/registration")//так же тут можно потребовать HttpServletRequest
    public String registration(User user){ //спринг автоматически может сконструировать целый объект из набора каких-то параметров
        userService.save(user);
        return "redirect:/"; //перенаправляем простым стрингом(redirect:) на главную страницу(/)
    }

}
