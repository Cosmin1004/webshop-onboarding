package com.youngculture.webshop_onboarding.repository;

import com.youngculture.webshop_onboarding.model.User;

public interface UserRepository {

    User findUserByEmail(String email);

    void saveUser(User user);

}
