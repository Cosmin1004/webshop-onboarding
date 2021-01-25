package com.youngculture.webshop_onboarding.controller;

import com.youngculture.webshop_onboarding.controller.handler.CookieHandler;
import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.impl.CartRepositoryImpl;
import com.youngculture.webshop_onboarding.repository.impl.OrderRepositoryImpl;
import com.youngculture.webshop_onboarding.service.CartService;
import com.youngculture.webshop_onboarding.service.Impl.CartServiceImpl;
import com.youngculture.webshop_onboarding.service.Impl.OrderServiceImpl;
import com.youngculture.webshop_onboarding.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartCountServlet extends HttpServlet {

    private List<Order> ordersPlaced = new ArrayList<>();
    private CartService cartService;
    private CookieHandler cookieHandler;
    private String count;


    public CartCountServlet() {
        cartService = new CartServiceImpl(
                new CartRepositoryImpl());
        cookieHandler = new CookieHandler();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(true);
        User currentSessionUser = (User) session.getAttribute("currentSessionUser");

        if (currentSessionUser != null) {
            ordersPlaced = cartService.getUserCart(currentSessionUser);
        } else {
            ordersPlaced = cookieHandler.getAnonymousCart(request);
        }
        count = String.valueOf(ordersPlaced.size());

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(count);
    }
}
