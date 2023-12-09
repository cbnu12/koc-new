package com.koc.user.application.port.out;

import com.koc.user.domain.token.TokenDto;

public interface SaveTokenPort {
    void save(TokenDto dto);
}
