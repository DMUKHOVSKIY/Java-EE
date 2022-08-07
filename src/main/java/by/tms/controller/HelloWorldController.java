package by.tms.controller;

import by.tms.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//по какому пути будет доступен этот контроллер. Как сюда будут приходить запросы:
@Controller//расширяет аннотацию @Component
@RequestMapping("/hello") //корневой путь к этому контроллеру (можно не указывать, но тогда надо в каждом
//методе указывать все уточняющие пути. Но  тае делать не надо
public class HelloWorldController {


    //Этот метод так же может иметь дополнительный путь.
    //Если в сервлетах 1 сервлет на 1 путь, то контроллер может обрабатывать много разных путей
    //Может быть куча get методов, куча post методов и тд.
    //Путь: GET localhost:8080/hello
    @GetMapping//аналог doGet в сервлете. Если нет доп. пути, то метод работает по пути /hello
    public String helloWorld(Model model) {//Просим Spring нам отдать модель, а возвращаем view
        model.addAttribute("message", "Hello World!");//Это как req.serAttribute("message", "HelloWorld!")
        return "hello"; //нету ни префикса, ни суффикса. Потому что viewResolver позволяет нам это сделать, он сам подставит
        //а так бы надо было писать  ("/pages/hello.jsp"). Это как getServletContext.getRequestDispatcher("/hello.jsp").forward(req,resp)
    }

    //ModelAndView проще использовать, чем Model
    public ModelAndView anotherHelloWorld(ModelAndView modelAndView){//ModelAndView - включает в себя и view и модель
        modelAndView.setViewName("hello");
        modelAndView.addObject("message", "Hello World!");
        return modelAndView;
    }

    //Путь: GET localhost:8080/hello/test
    @GetMapping("/test") //если не навешивать доп. путь, то будет ошибка(2 метода с одинаковым путем /hello)
    public String helloWorld2(String name, @RequestParam(defaultValue = "0")/*по умолчанию age = 0*/ int age, Model model) {
        model.addAttribute("myName", name);//req.setAttribute("username", req.getParameter("name"))
        model.addAttribute("myAge", age);//Spring Web модуль имеет под капотом ещё и набор конвертёров,
        //которые конвертируют в стандартные типы (так бы надо было: Integer.parseInteger(req.getParameter("age")))
        return "test";
    }

    //Get localhost:8080/hello/{number}  - в сервлетах обрабатывать такие секции очень сложно (доставать значение из секции)
    @GetMapping("/{number}")//{} указывают на то, что в этой секции меняются значения(динамическая секция)
    public String helloWorld3(/*если мы напишем int number, то это будет считаться параметром, а не секцией, поэтому мы пишем аннотацию и главное, чтобы названия(number) совпадали*/@PathVariable(/*"тут можно уточнять"*/) int number, Model model/*модель нужна если надо переносить данные с контроллера на jsp*/) {
        model.addAttribute("myNumber", number);
        return "number";
    }

    //Мы знаем, что фильтры крепятся к диспатчер сервлетам и фильтр отрабатывает еще до того, как на него придет запрос. Мы хотим отлавливать запрос, когда он уже конкретно идёт на определённый контроллер. Для этого у нас есть Spring Interceptor
}
