package by.tms.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExControllerAdvice { //Это такой класс-советчик для контроллеров (он ловит эксепшены). Нужен не только для агрегирования методов помеченных аннотацией @ExceptionHandler, но и некоторых других вещей связанных с request и тд.
    @ExceptionHandler(RuntimeException.class) // это метод из контроллера(класс HelloWorldController). Сюда надо передавать Exception, который может происходить где угодно
    public String handleException(RuntimeException e){
//      log.error("Exception: {}", e.getMessage());
        return "error";
    }

    //С @ExceptionHandler не работает ModelAndView
//    @ExceptionHandler(RuntimeException.class)
//    public String handleException1(RuntimeException e, Model model){
//        model.addAttribute("message", e.getMessage());
//      log.error("Exception: {}", e.getMessage());
//        return "error";
//    }
}
