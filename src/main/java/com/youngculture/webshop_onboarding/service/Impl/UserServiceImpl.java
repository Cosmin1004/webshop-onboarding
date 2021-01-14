package com.youngculture.webshop_onboarding.service.Impl;

import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.UserRepository;
import com.youngculture.webshop_onboarding.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final String secretKey = "secretKey";

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        String pwd = user.getPassword();
        String encryptPwd = EncryptDecrypt.encrypt(pwd, secretKey);
        user.setPassword(encryptPwd);

        userRepository.saveUser(user);
    }

    @Override
    public boolean validate(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && user.getPassword()
                .equals(EncryptDecrypt.encrypt(password, secretKey))) {
            return true;
        }

        return false;
    }

}