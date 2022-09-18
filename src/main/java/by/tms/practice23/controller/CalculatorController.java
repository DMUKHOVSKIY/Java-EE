package by.tms.practice23.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calc")
public class CalculatorController {
    @GetMapping
    public String page() {
        SecurityContext context = SecurityContextHolder.getContext();  //По факту мы можем доставать это где угодно на протяжении работы приложения
        Authentication authentication = context.getAuthentication(); //этот компонент инкапсулирует внутри себя авторизованного пользователя
        System.out.println(authentication.getPrincipal()); //Principal - данные для входа (username, password)
        System.out.println(authentication.getCredentials()); //Credential - авторизованный пользователь
        return "calc";
    }
}
