package controller.servlet;

import dto.UserDto;
import model.User;
import dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUsersList", value = "/userslist")
public class UsersListServlet extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(" <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                "          integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">");
        out.println("<h1>UsersList</h1>");
        out.println("<table class=\"table\">");
        out.println("<thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Username</th>\n" +
                "      <th scope=\"col\">Email</th>\n" +
                "    </tr>\n" +
                "  </thead>");
        for (User user : userDao.getUsers()) {
            UserDto userDto = new UserDto(user);
            out.println("<tr><td>" + "Username: " + userDto.getName() + "</td>" + "<td>" + "User email: " + userDto.getEmail() + "</td></tr>");
        }
        out.println("</table>");
        out.println("<button type=\"button\" class=\"btn btn-success\" onclick=\"location.href='http://localhost:8080/logout'\">LogOut</button>\n");
        out.println("<a href=/index style=\"text-decoration: none\">\n" +
                "        <button type=\"button\" class=\"btn btn-success\">Home</button>\n" +
                "    </a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
