package com.youngculture.webshop_onboarding.controller;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.impl.OrderRepositoryImpl;
import com.youngculture.webshop_onboarding.repository.impl.ProductRepositoryImpl;
import com.youngculture.webshop_onboarding.service.Impl.OrderServiceImpl;
import com.youngculture.webshop_onboarding.service.Impl.ProductServiceImpl;
import com.youngculture.webshop_onboarding.service.OrderService;
import com.youngculture.webshop_onboarding.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class OrderServlet extends HttpServlet {

    private OrderService orderService;
    private ProductService productService;

    public OrderServlet() {
        orderService = new OrderServiceImpl(
                new OrderRepositoryImpl());
        productService = new ProductServiceImpl(
                new ProductRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("addProduct") != null){
            Order order = setOrder(request);
            orderService.saveOrUpdateOrder(order);
        } else if(request.getParameter("removeProduct") != null) {
            request.setAttribute("cartRendered", true);

        }
        request.getRequestDispatcher("product.jsp")
                .forward(request, response);
    }

    private Order setOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentSessionUser");
        String productName = request.getParameter("addProduct");
        Product product = productService.getProductByName(productName);

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);

        return order;
    }
}
