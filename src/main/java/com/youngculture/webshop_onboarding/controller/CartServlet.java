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

public class CartServlet extends HttpServlet {

    private List<Order> ordersPlaced = new ArrayList<>();
    private CartService cartService;
    private OrderService orderService;
    private CookieHandler cookieHandler;
    private boolean cartRendered = false;


    public CartServlet() {
        cartService = new CartServiceImpl(
                new CartRepositoryImpl());
        orderService = new OrderServiceImpl(
                new OrderRepositoryImpl());
        cookieHandler = new CookieHandler();
    }

    //get cart both for logged & anonymous user
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cartRendered = true;
        HttpSession session = request.getSession(true);
        User currentSessionUser = (User) session.getAttribute("currentSessionUser");

        if (currentSessionUser != null) {
            ordersPlaced = cartService.getUserCart(currentSessionUser);
        } else {
            ordersPlaced = cookieHandler.getAnonymousCart(request);
        }

        request.setAttribute("ordersPlaced", ordersPlaced);
        request.setAttribute("cartRendered", cartRendered);
        request.getRequestDispatcher("product.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("currentSessionUser");
        orderService.sendOrders(user);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("The order has been sent!");
    }
}
