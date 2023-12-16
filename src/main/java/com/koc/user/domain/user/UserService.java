package com.koc.user.domain.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto withdraw(UserDto userDto) {
        var user = new User(userDto.getId(), userDto.getKocId(), userDto.getPassword(), userDto.getRefreshToken()
                , userDto.getUserStatus(), userDto.getEmail());
        user.withdraw();
        return user.toDto();
    }
}
