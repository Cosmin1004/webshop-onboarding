package com.youngculture.webshop_onboarding.controller.filter;

import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.impl.UserRepositoryImpl;
import com.youngculture.webshop_onboarding.service.Impl.UserServiceImpl;
import com.youngculture.webshop_onboarding.service.UserService;

import javax.servlet.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFilter implements Filter {


    private UserService userService;
    private boolean filtered;
    private String message;

    @Override
    public void init(FilterConfig filterConfig) {
        userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        loginInFilter(servletRequest, servletResponse);

        registerFilter(servletRequest, servletResponse);

        if( ! filtered || servletRequest.getParameter("logout") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    private void loginInFilter(ServletRequest servletRequest,
                               ServletResponse servletResponse) throws ServletException, IOException {
        filtered = true;
        if(servletRequest.getParameter("login") != null) {
            User userFound = userService.validate(
                    servletRequest.getParameter("email"), servletRequest.getParameter("pwd"));
            if (userFound == null) {
                message = "Login problem: Invalid email or password!";
                servletRequest.setAttribute("message", message);

                servletRequest.getRequestDispatcher("product.jsp")
                        .forward(servletRequest, servletResponse);

            } else {
                filtered = false;
            }
        }
    }

    private void registerFilter(ServletRequest servletRequest,
                                ServletResponse servletResponse)
            throws ServletException, IOException {
        if (servletRequest.getParameter("register") != null) {
            filtered = true;
            if (userService.getUserByEmail(servletRequest.getParameter("regEmail")) != null) {
                message = "Registration problem: This email is already used!";
                servletRequest.setAttribute("message", message);

                servletRequest.getRequestDispatcher("product.jsp")
                        .forward(servletRequest, servletResponse);
            } else if (!validatePasswordField(servletRequest.getParameter("regPwd"))) {
                message = "Registration problem: Password must have at least 8 characters " +
                        "(with minimum 1 digit, no special characters)!";
                servletRequest.setAttribute("message", message);

                servletRequest.getRequestDispatcher("product.jsp")
                        .forward(servletRequest, servletResponse);
            } else {
                filtered = false;
            }
        }
    }

    private boolean validatePasswordField(String string){
        Matcher matcher = Pattern.compile
                ("^(?=.*[0-9])(?=.*[a-zA-Z])\\w{8,}$")
                .matcher(string);
        if(matcher.find()) {
            return true;
        }
        return  false;
    }

    @Override
    public void destroy() {
        userService = null;
    }
}