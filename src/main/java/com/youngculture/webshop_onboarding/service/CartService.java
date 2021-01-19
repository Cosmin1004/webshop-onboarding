package com.youngculture.webshop_onboarding.service;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.User;

import java.util.List;

public interface CartService {

    List<Order> getUserCart(User user);

}
