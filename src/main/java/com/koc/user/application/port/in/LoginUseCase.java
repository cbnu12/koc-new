package com.koc.user.application.port.in;

import com.koc.user.domain.token.TokenDto;

public interface LoginUseCase {
    TokenDto login(String code);
}
