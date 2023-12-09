package com.koc.user.application.service;

import com.koc.user.domain.UserDto;
import com.koc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final com.koc.user.domain.UserService userService;

    public User withdraw(Long id) {
        User user = userService
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재 하지 않습니다."));
        user.withdraw();
        return userService.save(user);
    }

    public UserDto findById(long id) {
        return userService.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 사용자")).toDto();
    }
}
