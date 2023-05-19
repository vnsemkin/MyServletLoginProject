package controller.filter;

import constants.Constants;

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
        String defaultHttp = Constants.INDEX_URL;
        String url = httpRequest.getRequestURI();
        String userName = (String) httpRequest.getSession().getAttribute(Constants.LOGIN_URL);

        if ((userName == null && isAuthenticationUrl(url) || url.equals(defaultHttp))
                || (userName != null && !isAuthenticationUrl(url))) {
            httpRequest.getServletContext().getRequestDispatcher(url).forward(request, response);
        } else {
            httpResponse.sendRedirect(defaultHttp);
        }
    }

    private boolean isAuthenticationUrl(String url) {
        return url.equals(Constants.LOGIN_URL) || url.equals(Constants.SIGNUP_URL);
    }
}
