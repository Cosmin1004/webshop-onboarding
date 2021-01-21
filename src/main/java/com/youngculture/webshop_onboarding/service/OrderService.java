package com.youngculture.webshop_onboarding.service;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.User;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void saveOrUpdateOrder(Order order);

    void sendOrders(User user);

    void deleteOrder(User user, Product product);

    Map<Long, List<Order>> getUserOrdersGroupedByReference(User user);

}
