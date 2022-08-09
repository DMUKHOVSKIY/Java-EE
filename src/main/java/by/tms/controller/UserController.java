package by.tms.controller;

import by.tms.entity.User;
import by.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/registration")
//    public String registration() {
//        return "reg";
//    }
    //Registration fore modelAttribute
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());//для того, чтобы форма jsp работала мы должны создать нового юзера
        return "reg";
    }

    @PostMapping("/registration")//так же тут можно потребовать HttpServletRequest
    public String registration(@Valid @ModelAttribute("newUser") /*сообщаем, что будем валидировать нашего юзера*/User user, BindingResult bindingResult, Model model) {//спринг автоматически может сконструировать целый объект из набора каких-то параметров
        if (bindingResult.hasErrors()) {//помогает нам понять есть ли ошибки и если есть, то какие. Мешок, куда сбрасывается весь результат валидаций
            //@ ModelAttribute (то же самое, что и model.addAttribute("newUser", user)) - Если мы ввели данные и они все не подходят, нас перенаправляют в этот метод, а аттрибут остается старый (из метода get)
//            //получаем все ошибки, которые вообще есть ВРУЧНУЮ
//            Map<String, String> map = new HashMap<>();
//            for (FieldError allError : bindingResult.getFieldErrors()) {
//                map.put(allError.getField(), allError.getDefaultMessage());
//            }
//            model.addAllAttributes(map);//сразу всю мапу
            return "reg";//возвращаем ту же самую страницу с уже ошибками
        }
        userService.save(user);
        return "redirect:/"; //перенаправляем простым стрингом(redirect:) на главную страницу(/)
    }
//спринг формы
}
