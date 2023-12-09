package com.koc.user.application.port.in;

import com.koc.user.domain.user.UserDto;

public interface GetUserUseCase {
    UserDto getUser(Long id);
}
