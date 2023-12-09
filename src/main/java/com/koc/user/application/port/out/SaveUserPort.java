package com.koc.user.application.port.out;

import com.koc.user.domain.UserDto;

public interface SaveUserPort {
    void save(UserDto dto);
}
