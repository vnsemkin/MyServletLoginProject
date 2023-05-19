package controller.servlet;

import constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletHome", value = "/index")
public class HomeServlet extends HttpServlet {
    private static final String INDEX_JSP = "/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionUserName = (String) req.getSession().getAttribute(Constants.NAME_ATTRIBUTE);
        if (req.getSession() != null && sessionUserName != null) {
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>You are login as :" + sessionUserName + "</font>");
            getServletContext().getRequestDispatcher(INDEX_JSP).include(req, resp);
        } else {
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>You have to login</font>");
            getServletContext().getRequestDispatcher(INDEX_JSP).include(req, resp);
        }
    }
}
