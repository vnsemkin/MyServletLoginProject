package controller.servlet;

import constants.Constants;
import dao.UserDao;
import dto.UserDto;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Objects;

@WebServlet(name = "ServletSignup", value = "/signup")
public class SignupServlet extends HttpServlet {
    UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/signup.jsp";
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(Constants.NAME_ATTRIBUTE);
        String email = request.getParameter(Constants.EMAIL_ATTRIBUTE);
        String password = request.getParameter(Constants.PASSWORD_ATTRIBUTE);
        if (Objects.nonNull(name) && Objects.nonNull(email) && Objects.nonNull(password)) {
            for (User user : userDao.getUsers()) {
                if (name.equals(user.getName())) {
                    PrintWriter out = response.getWriter();
                    out.println("<font color=red>User already exist!</font>");
                    getServletContext().getRequestDispatcher(Constants.SIGNUP_URL).include(request, response);
                    return;
                }
            }
            long id = userDao.getUsers().size();
            UserDto userDto = userDao.createUser(new User(id + 1, name, email, password));
            for (User user : userDao.getUsers()) {
                if (user.getName().equals(userDto.getName())) {
                    PrintWriter out = response.getWriter();
                    out.println("<font color=red>User :" + userDto + " -created! </font>");
                    getServletContext().getRequestDispatcher(Constants.INDEX_JSP).include(request, response);
                    return;
                }
            }
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User :" + userDto + " -not -created! </font>");
            getServletContext().getRequestDispatcher(Constants.INDEX_JSP).include(request, response);

        } else {
            PrintWriter out = response.getWriter();
            out.println("<font color=red>One ore more fields could`t be empty!</font>");
            getServletContext().getRequestDispatcher(Constants.SIGNUP_URL).include(request, response);
        }
    }
}
