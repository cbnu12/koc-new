package com.koc.user.application.port.in;

import com.koc.user.domain.TokenDto;

public interface LoginUseCase {
    TokenDto login(String code);
}
