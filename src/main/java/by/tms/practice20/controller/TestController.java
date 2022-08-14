package by.tms.practice20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(String name, boolean isVisible, Model model) {
        model.addAttribute("myName", name);
        model.addAttribute("isVisible", isVisible);
        return "hello";
    }

    @GetMapping("/history")
    public String history(Model model){
        List<String> list = new ArrayList<>();
        list.add("Test 1");
        list.add("Test 2");
        list.add("Test 3");
        list.add("Test 4");
        model.addAttribute("history", list);
        return "hello";
    }


}
