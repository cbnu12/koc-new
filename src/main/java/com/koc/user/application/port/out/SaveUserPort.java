package com.koc.user.application.port.out;

import com.koc.user.domain.user.UserDto;

public interface SaveUserPort {
    void save(UserDto dto);
}
