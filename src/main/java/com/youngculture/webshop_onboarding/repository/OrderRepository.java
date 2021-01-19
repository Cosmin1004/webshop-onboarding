package com.youngculture.webshop_onboarding.repository;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.User;

import javax.transaction.Status;

public interface OrderRepository {

    void saveOrder(Order order);

    void updateOrderQuantity(Order order);

    void updateOrderStatus(Order order, Status status);

    Order findOrderByUserAndProduct(User user, Product product);

}
