package com.koc.user.application.port.out;

import com.koc.user.domain.UserDto;

public interface LoadUserPort {
    UserDto load(Long id);
}
