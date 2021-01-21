package com.youngculture.webshop_onboarding.controller;

import com.youngculture.webshop_onboarding.controller.handler.CookieHandler;
import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.impl.CartRepositoryImpl;
import com.youngculture.webshop_onboarding.repository.impl.OrderRepositoryImpl;
import com.youngculture.webshop_onboarding.repository.impl.ProductRepositoryImpl;
import com.youngculture.webshop_onboarding.service.CartService;
import com.youngculture.webshop_onboarding.service.Impl.CartServiceImpl;
import com.youngculture.webshop_onboarding.service.Impl.OrderServiceImpl;
import com.youngculture.webshop_onboarding.service.Impl.ProductServiceImpl;
import com.youngculture.webshop_onboarding.service.OrderService;
import com.youngculture.webshop_onboarding.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OrderServlet extends HttpServlet {

    private OrderService orderService;
    private ProductService productService;
    private CartService cartService;
    private CookieHandler cookieHandler;
    private List<Order> ordersPlaced;
    private Map<Long, List<Order>> mapOrdersSent;
    private boolean myOrdersRendered = false;

    public OrderServlet() {
        orderService = new OrderServiceImpl(
                new OrderRepositoryImpl());
        productService = new ProductServiceImpl(
                new ProductRepositoryImpl());
        cartService = new CartServiceImpl(
                new CartRepositoryImpl());
        cookieHandler = new CookieHandler();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User currentSessionUser = getCurrentUser(request);
        //add product to cart both for logged & anonymous user
        if (request.getParameter("productName") != null) {
            if (currentSessionUser != null) {
                Order order = setOrder(request);
                orderService.saveOrUpdateOrder(order);
            } else {
                cookieHandler.saveToCookies(
                        request, response, request.getParameter("productName"));
            }
            //remove product from cart both for logged & anonymous user
        } else if (request.getParameter("removeProduct") != null) {
            if (currentSessionUser != null) {
                orderService.deleteOrder(currentSessionUser,
                        productService.getProductByName(request.getParameter("removeProduct")));
                ordersPlaced = cartService.getUserCart(currentSessionUser);
            } else {
                cookieHandler.removeFromCookies
                        (request, response, request.getParameter("removeProduct"));
                ordersPlaced = setNewOrderList(cookieHandler.getAnonymousCart(request),
                        request.getParameter("removeProduct"));
            }
            request.setAttribute("ordersPlaced", ordersPlaced);
            request.setAttribute("cartRendered", true);
        }
        request.getRequestDispatcher("product.jsp")
                .forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        myOrdersRendered = true;

        mapOrdersSent = orderService
                .getUserOrdersGroupedByReference(getCurrentUser(request));

        request.setAttribute("ordersSent", mapOrdersSent);
        request.setAttribute("myOrdersRendered", myOrdersRendered);
        request.getRequestDispatcher("product.jsp")
                .forward(request, response);
    }

    private Order setOrder(HttpServletRequest request) {
        String productName = request.getParameter("productName");
        Product product = productService.getProductByName(productName);

        Order order = new Order();
        order.setUser(getCurrentUser(request));
        order.setProduct(product);

        return order;
    }

    private User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("currentSessionUser");
    }

    private List<Order> setNewOrderList(List<Order> orderList, String productName) {
        orderList.removeIf(order -> order.getProduct().getName().equals(productName));
        return orderList;
    }

}
