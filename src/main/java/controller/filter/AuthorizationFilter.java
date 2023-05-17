package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", value = "/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String defaultHttp = "/index";
        String url = httpRequest.getRequestURI();
        String userName = (String) httpRequest.getSession().getAttribute("name");
        if (userName == null && isAuthenticationUrl(url) || url.equals(defaultHttp)) {
            httpRequest.getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (userName != null && !isAuthenticationUrl(url)) {
            httpRequest.getServletContext().getRequestDispatcher(url).forward(request, response);
        } else {
            httpResponse.sendRedirect(defaultHttp);
        }
    }

    private boolean isAuthenticationUrl(String url) {
        return url.equals("/login") || url.equals("/signup");
    }
}
