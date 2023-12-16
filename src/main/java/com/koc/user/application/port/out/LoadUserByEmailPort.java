package com.koc.user.application.port.out;

import com.koc.user.domain.user.UserDto;

import java.util.Optional;

public interface LoadUserByEmailPort {
    Optional<UserDto> load(String email);
}
