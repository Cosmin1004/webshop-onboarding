package com.youngculture.webshop_onboarding.repository;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.Status;
import com.youngculture.webshop_onboarding.model.User;

import java.util.List;

public interface OrderRepository {

    Order findOrderByUserAndProduct(User user, Product product);

    List<Order> findOrdersByUserAndStatus(User user);

    void saveOrder(Order order);

    void updateOrderQuantity(Order order);

    void updateOrderStatus(User user, Status currentStatus, Status newStatus);

    void updateOrderReference(User user, Long reference);

    void deleteOrder(User user, Product product);

}
