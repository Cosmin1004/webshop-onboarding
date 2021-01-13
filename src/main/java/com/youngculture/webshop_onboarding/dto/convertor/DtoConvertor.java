package com.youngculture.webshop_onboarding.dto.convertor;

import com.youngculture.webshop_onboarding.dto.RegisterDto;
import com.youngculture.webshop_onboarding.model.User;

public class DtoConvertor {

    public User convertRegisterDtoToUserDto(RegisterDto registerDto) {
        User user = new User();

        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());

        return user;
    }

}
