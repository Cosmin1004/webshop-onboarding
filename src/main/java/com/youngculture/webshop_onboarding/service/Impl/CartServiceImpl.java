package com.youngculture.webshop_onboarding.service.Impl;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.CartRepository;
import com.youngculture.webshop_onboarding.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Order> getUserCart(User user) {
       return cartRepository.findUserCart(user);
    }

}
