package com.youngculture.webshop_onboarding.service.Impl;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.OrderRepository;
import com.youngculture.webshop_onboarding.service.OrderService;

import javax.transaction.Status;

public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrUpdateOrder(Order order) {
        Order orderDb = orderRepository.findOrderByUserAndProduct(
                order.getUser(), order.getProduct());
        if(orderDb == null) {
            orderRepository.saveOrder(order);
        } else {
            orderRepository.updateOrderQuantity(order);
        }
    }

    @Override
    public void updateOrderStatus(Order order, Status status) {
        orderRepository.updateOrderStatus(order, status);
    }
}
