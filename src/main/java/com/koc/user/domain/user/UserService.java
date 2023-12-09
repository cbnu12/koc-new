package com.koc.user.domain.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto withdraw(UserDto userDto) {
        var user = new User(userDto.getId(), userDto.getKocId(), userDto.getPw(), userDto.getRefreshToken()
                , userDto.getKakaoId(), userDto.getLoginType(), userDto.getUserStatus(), userDto.getEmail());
        user.withdraw();
        return user.toDto();
    }
}
