package com.youngculture.webshop_onboarding.service.Impl;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.Status;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.OrderRepository;
import com.youngculture.webshop_onboarding.service.OrderService;

import java.util.*;


public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrUpdateOrder(Order order) {
        Order orderDb = orderRepository.findOrderByUserAndProduct(
                order.getUser(), order.getProduct());
        if (orderDb == null) {
            orderRepository.saveOrder(order);
        } else {
            orderRepository.updateOrderQuantity(order);
        }
    }

    @Override
    public void sendOrders(User user) {
        Long reference = Calendar.getInstance().getTimeInMillis();
        orderRepository.updateOrderReference(user, reference);
        orderRepository.updateOrderStatus(user, Status.PLACED, Status.SENT);
    }

    @Override
    public void deleteOrder(User user, Product product) {
        orderRepository.deleteOrder(user, product);
    }

    @Override
    public Map<Long, List<Order>> getUserOrdersGroupedByReference(User user) {
        Map<Long, List<Order>> ordersByReference = new HashMap<>();
        List<Order> orders = orderRepository.findOrdersByUserAndStatus(user);
        for (Order order : orders) {
            Long reference = order.getReference();
            if (!ordersByReference.containsKey(reference)) {
                ordersByReference.put(reference, new ArrayList<>());
            }
            ordersByReference.get(reference).add(order);
        }
        return ordersByReference;
    }
}
