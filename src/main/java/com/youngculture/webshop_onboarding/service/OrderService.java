package com.youngculture.webshop_onboarding.service;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.User;

import javax.transaction.Status;

public interface OrderService {

    void saveOrUpdateOrder(Order order);

    void updateOrderStatus(Order order, Status status);

}
