package com.youngculture.webshop_onboarding.controller;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.impl.CartRepositoryImpl;
import com.youngculture.webshop_onboarding.service.CartService;
import com.youngculture.webshop_onboarding.service.Impl.CartServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartServlet extends HttpServlet {

    private List<Order> orders = new ArrayList<>();
    private CartService cartService;
    private boolean cartRendered = false;

    public CartServlet() {
        cartService = new CartServiceImpl(
                new CartRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cartRendered = true;
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("currentSessionUser");
        orders = cartService.getUserCart(user);
        request.setAttribute("orders", orders);
        request.setAttribute("cartRendered", cartRendered);

        request.getRequestDispatcher("product.jsp")
                    .forward(request, response);
    }
}
