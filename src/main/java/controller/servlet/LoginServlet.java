package controller.servlet;

import constants.Constants;
import dao.UserDao;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "ServletLogin", value = "/login")
public class LoginServlet extends HttpServlet {

    UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher(Constants.LOGIN_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String approvedUserPassword = null;
        String sessionUserName = request.getParameter(Constants.NAME_ATTRIBUTE);
        String sessionUserPassword = request.getParameter(Constants.PASSWORD_ATTRIBUTE);
        for (User user : userDao.getUsers()) {
            if (user.getName().equals(sessionUserName)) {
                approvedUserPassword = user.getPassword();
                break;
            }
        }
        if (Objects.nonNull(sessionUserName) && Objects.nonNull(sessionUserPassword)&&Objects.nonNull(approvedUserPassword)) {
            if (approvedUserPassword.equals(sessionUserPassword)) {
                HttpSession session = request.getSession();
                session.setAttribute(Constants.NAME_ATTRIBUTE, sessionUserName);
                session.setMaxInactiveInterval(30 * 60);
                Cookie userNameCookie = new Cookie(Constants.USER_ATTR, sessionUserName);
                userNameCookie.setMaxAge(30 * 60);
                response.addCookie(userNameCookie);
                response.sendRedirect(Constants.WELCOME_URL);
            }
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Wrong password!</font>");
            getServletContext().getRequestDispatcher(Constants.LOGIN_JSP).include(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User not found!</font>");
            getServletContext().getRequestDispatcher(Constants.LOGIN_JSP).include(request, response);
        }
    }
}
