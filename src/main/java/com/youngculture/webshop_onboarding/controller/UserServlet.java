package com.youngculture.webshop_onboarding.controller;

import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.impl.UserRepositoryImpl;
import com.youngculture.webshop_onboarding.service.Impl.UserServiceImpl;
import com.youngculture.webshop_onboarding.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet() {
        userService = new UserServiceImpl(
                new UserRepositoryImpl());
    }

    /**
     * LOGIN/LOGOUT and REGISTER
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //there are some filters (both for Login and Register) in UserFilter
        if (request.getParameter("login") != null) {
            User user = userService.getUserByEmail(request.getParameter("email"));
            setUserSession(request, user);
        } else if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.invalidate();
        } else if (request.getParameter("register") != null) {
            User user = getUserFromRequest(request);
            userService.saveUser(user);
            setUserSession(request, user);
        }
        request.getRequestDispatcher("product.jsp")
                .forward(request, response);
    }

    private void setUserSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("currentSessionUser", user);
    }

    private User getUserFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("regEmail");
        String password = request.getParameter("regPwd");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
