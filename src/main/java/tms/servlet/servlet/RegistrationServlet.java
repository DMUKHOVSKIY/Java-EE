package tms.servlet.servlet;

import tms.servlet.entity.User;
import tms.servlet.storage.InMemoryUserStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ="/reg")
public class RegistrationServlet extends HttpServlet {

    private final InMemoryUserStorage inMemoryUserStorage = new InMemoryUserStorage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);

        inMemoryUserStorage.save(user);

    }
}
