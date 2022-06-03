package tms.servlet.servlet;

import tms.servlet.entity.User;
import tms.servlet.storage.InMemoryUserStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/auth")
public class AuthenticationServlet extends HttpServlet {

    private final InMemoryUserStorage inMemoryUserStorage = new InMemoryUserStorage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> byUserName = inMemoryUserStorage.findByUserName(username);
        if(byUserName.isPresent()){
            User user = byUserName.get();
            if(user.getPassword().equals(password)){
                req.getSession().setAttribute("currentUser", user);
            }else{
                resp.getWriter().println("Wrong password!");
            }
            }else{
            resp.getWriter().println("User not found!");

        }



    }
}
