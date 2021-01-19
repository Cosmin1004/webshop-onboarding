package com.youngculture.webshop_onboarding.repository;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.User;

import java.util.List;

public interface CartRepository {

    List<Order> findUserCart(User user);

}
