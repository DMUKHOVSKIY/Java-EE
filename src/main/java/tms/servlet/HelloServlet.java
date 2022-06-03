package tms.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/hello", name = "HelloServlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        req.setAttribute("name", name);
        //getServletContext().setAttribute("name", name);// так мы можем получить доступ к Application scope (положили атрибут)
        //  getServletContext().getRequestDispatcher("/test").forward(req, resp);//пренапрявляемся с одного сервлета на другой (пробросили), но в URL этого не видно / request не теряется/только на какой-нибудь внутренний ресурс
        resp.sendRedirect("/test");//отличается от "Dispatcher" тем, что это команда браузеру сформировать абсолютно новый запрос(request теряется и делается новый на другой URL)/в URL это видно/можно даже на Google
/*
        req.getSession().setAttribute("name", name); // Session Scope
        resp.getWriter().println("<h1>Hello" + name + "</h1>");*/
// Cookies - способ хранения данных сервера на стороне клиента (в браузере)
    }
}
