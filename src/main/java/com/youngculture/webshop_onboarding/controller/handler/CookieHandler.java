package com.youngculture.webshop_onboarding.controller.handler;

import com.google.gson.Gson;
import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.repository.impl.ProductRepositoryImpl;
import com.youngculture.webshop_onboarding.service.Impl.ProductServiceImpl;
import com.youngculture.webshop_onboarding.service.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CookieHandler {

    private ProductService productService;

    public CookieHandler() {
        productService = new ProductServiceImpl(
                new ProductRepositoryImpl());
    }

    public List<Order> getAnonymousCart(HttpServletRequest request) {
        List<Order> orders = new ArrayList<>();
        List<OrderCookie> listOrdersFromCookies = getFromCookies(request);
        for(OrderCookie orderCookie : listOrdersFromCookies) {
            Order order = new Order();
            order.setProduct(productService
                    .getProductByName(orderCookie.getProductName()));
            order.setQuantity(orderCookie.getQuantity());
            orders.add(order);
        }
        return orders;
    }

    public void saveToCookies(HttpServletRequest request, HttpServletResponse response, String product) {
        Gson gson = new Gson();
        OrderCookie order = new OrderCookie();
        order.setProductName(product);
        order.setQuantity(1);

        Cookie cookie = new Cookie("order" + (int) (Math.random() * (1000 - 1)) + 1,
                URLEncoder.encode(gson.toJson(order)));
        if( orderCookieExist(request, product) != null)
        {
            updateOrderCookieQuantity(request, response, product);
        } else {
            response.addCookie(cookie);
        }
    }

    public void removeFromCookies(HttpServletRequest request, HttpServletResponse response, String productName) {
        Cookie cookie = getCookieByProduct(request, productName);
        if(cookie != null) {
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);
    }

    public void updateOrderCookieQuantity(HttpServletRequest request, HttpServletResponse response, String product) {
        Gson gson = new Gson();
        OrderCookie oldOrderCookie = orderCookieExist(request, product);
        Cookie cookieToBeUpdated = getCookieByProduct(request, product);

        OrderCookie newOrderCookie = new OrderCookie();
        newOrderCookie.setProductName(product);
        newOrderCookie.setQuantity(oldOrderCookie.getQuantity() + 1);

        cookieToBeUpdated.setValue(
                URLEncoder.encode(gson.toJson(newOrderCookie)));

        response.addCookie(cookieToBeUpdated);
    }

    private List<OrderCookie> getFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<OrderCookie> listOrdersFromCookies = new ArrayList<>();
        Gson gson = new Gson();
        for(Cookie cookie : cookies){
            if(cookie.getName().startsWith("order")){
                String jsonCookie = URLDecoder.decode(cookie.getValue());
                OrderCookie orderFromCookie = gson.fromJson(jsonCookie, OrderCookie.class);
                listOrdersFromCookies.add(orderFromCookie);
            }
        }
        return listOrdersFromCookies;
    }

    private OrderCookie orderCookieExist(HttpServletRequest request, String product) {
        List<OrderCookie> orderCookies = getOrdersFromCookies(request);
        for (OrderCookie orderCookie : orderCookies) {
            if(orderCookie.getProductName().equals(product)){
                return orderCookie;
            }
        }
        return null;
    }

    private List<OrderCookie> getOrdersFromCookies(HttpServletRequest request) {
        Cookie [] cookies = request.getCookies();
        List<OrderCookie> listOrdersFromCookies = new ArrayList<>();
        Gson gson = new Gson();
        for(Cookie cookie : cookies){
            if(cookie.getName().startsWith("order")){
                String jsonCookie = URLDecoder.decode(cookie.getValue());
                OrderCookie orderFromCookie = gson.fromJson(jsonCookie, OrderCookie.class);
                listOrdersFromCookies.add(orderFromCookie);
            }
        }
        return listOrdersFromCookies;
    }

    private Cookie getCookieByProduct(HttpServletRequest request, String product) {
        Cookie [] cookies = request.getCookies();
        Gson gson = new Gson();
        for(Cookie cookie : cookies){
            if(cookie.getName().startsWith("order")){
                String jsonCookie = URLDecoder.decode(cookie.getValue());
                OrderCookie orderFromCookie = gson.fromJson(jsonCookie, OrderCookie.class);
                if(orderFromCookie.getProductName().equals(product)) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
