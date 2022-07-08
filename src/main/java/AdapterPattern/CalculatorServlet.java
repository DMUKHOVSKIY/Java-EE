package AdapterPattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/calc")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("qwery");
        Operation op = QueryCalculatorAdapter.adapt(operation);
        double res = op.getNum1()+op.getNum2();
        req.setAttribute("result", res);
        getServletContext().getRequestDispatcher("/indx.jsp").forward(req, resp);
    }
}
