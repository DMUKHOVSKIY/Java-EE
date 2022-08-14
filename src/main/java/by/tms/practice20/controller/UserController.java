package by.tms.practice20.controller;

import by.tms.practice20.entity.User;
import by.tms.practice20.model.LoginUserModel;
import by.tms.practice20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/reg")
    public String reg(Model model) {
        model.addAttribute("newUser", new User());
        return "reg";
    }

    @PostMapping("/reg")
    public String reg(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reg";
        }
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userModel", new LoginUserModel());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute ("userModel") LoginUserModel userModel, BindingResult bindingResult, Model model, HttpSession session) { //Заменяем User user на LoginUserModel userModel, так как нам нужно достать из этой модели только пароль и юзернейм
        if (bindingResult.hasErrors()) {
            return "login";
        }
        Optional<User> byUsername = userService.findByUsername(userModel.getUsername());
        if (byUsername.isPresent()) {
            User user1 = byUsername.get();
            if (user1.getPassword().equals(userModel.getPassword())) {
                session.setAttribute("currentUser", user1);
                return "redirect:/";
            } else {
                model.addAttribute("message", "Wrong password!");
            }
        } else {
            model.addAttribute("message", "User not found!");
        }
        return "login";
    }

   @GetMapping("/logout") //по факту Logout должен быть post метод
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
   }
}
