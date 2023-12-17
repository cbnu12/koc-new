package com.koc.user.domain.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto withdraw(UserDto userDto) {
        var user = new User(userDto.id(), userDto.email(), userDto.password(), userDto.userStatus());
        user.withdraw();
        return UserDto.fromDomain(user);
    }

    public UserDto create(String email, String password) {
        return UserDto.fromDomain(User.create(email, password));
    }
}
