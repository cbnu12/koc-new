package com.koc.user.domain;

import com.koc.user.adapter.out.persistence.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserPort userPort;

    public User withdraw(Long id) {
        User user = userPort
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재 하지 않습니다."));
        user.withdraw();
        return userPort.save(user);
    }

    public UserDto findById(long id) {
        return userPort.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 사용자")).toDto();
    }
}
