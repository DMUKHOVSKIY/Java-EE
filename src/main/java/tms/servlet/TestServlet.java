package tms.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(req.getAttribute("name"));// параметры в URL, а атрибуты в самом request
        //String name = (String)getServletContext().getAttribute("name");//взяли атрибут(возвращает объект)
//        String name = (String) req.getSession().getAttribute("name"); // запрос с другого браузера ничего не покажет(другой клиент)
//        resp.getWriter().println(name);
    }
}
