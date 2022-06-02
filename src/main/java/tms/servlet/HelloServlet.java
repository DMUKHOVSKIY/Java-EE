package tms.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//localhost:8080/hello?name=Test - запрос в брузере
//@WebServlet(urlPatterns = "/hello",loadOnStartup = 0) // если 0 и выше, то экземпляр сревлета создается сразу,
// -1(по умолчанию) - создается, когда приходит 1-ый запрос(старутется отложенным образом). Цифры указывают порядок инициализации (сервлет с наименьшим значение вызывается раньше)
@WebServlet(urlPatterns = "/hello",loadOnStartup = 0)
public class HelloServlet extends HttpServlet {
// GET POST PUT DELETE - можем переопределить
//Tomcat сам автоматически вызывает метод doGet()


   /* @Override
    public void init() throws ServletException {// создает сервлет 1 раз (когда на него приходит 1-ый запрос) и вызывает методы (doGet())
        // и на него приходят все запросы с разных браузеров (сервлеты - одиночкм)
        super.init(); Использование конструкторов не безопасно (параметры конструтора)
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name"); // получаем значение определённого параметра(name) в запросе
        //resp.getWriter().println("Hello " + name);
        resp.getWriter().println("<h1>Hello " + name+ "</h1>");
    }
}
//Внутри Tomcat находится контейнер и он управляет всеми сервлетами
// (создает, вызывает методы, удаляет)
//Жизненный цикл сервлета:
//1. Init      |
//2. Service   |-это методы, только Service вызывается многократно
//3. Destroy   |