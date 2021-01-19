package com.youngculture.webshop_onboarding.service;

import com.youngculture.webshop_onboarding.model.User;

public interface UserService {

    void saveUser(User user);

    User getUserByEmail(String email);

    User validate(String email, String password);

}
