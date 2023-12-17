package com.koc.user.domain.user;

import com.koc.user.adapter.out.persistence.UserEntity;
import com.koc.user.domain.vo.UserStatus;

public record UserDto(
        Long id,
        String email,
        String password,
        UserStatus userStatus) {

    public static UserDto fromEntity(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getEmail(), entity.getPassword(), entity.getUserStatus());
    }

    public static UserDto fromDomain(User domain) {
        return new UserDto(domain.getId().value(),
                domain.getEmail().value(),
                domain.getPassword().value(),
                domain.getUserStatus());
    }
}
