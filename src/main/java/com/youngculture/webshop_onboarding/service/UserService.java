package com.youngculture.webshop_onboarding.service;

import com.youngculture.webshop_onboarding.model.User;

public interface UserService {

    void saveUser(User user);

    boolean validate(String email, String password);

}
