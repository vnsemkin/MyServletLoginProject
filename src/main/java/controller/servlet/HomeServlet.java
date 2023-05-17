package controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletHome", value = "/index")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionUserName = (String) req.getSession().getAttribute("name");
        if (req.getSession() != null && sessionUserName != null) {
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>You are login as :" + sessionUserName + "</font>");
            getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
        } else {
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>You have to login</font>");
            getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
        }
    }
}
