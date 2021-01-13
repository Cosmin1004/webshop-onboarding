package com.youngculture.webshop_onboarding.service.Impl;

import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.UserRepository;
import com.youngculture.webshop_onboarding.service.UserService;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        String pwd = user.getPassword();
        final String secretKey = "secretKey";
        String encryptPwd = EncryptDecrypt.encrypt(pwd, secretKey);
        user.setPassword(encryptPwd);

        userRepository.saveUser(user);
    }

}
