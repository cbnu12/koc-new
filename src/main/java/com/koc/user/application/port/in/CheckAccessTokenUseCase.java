package com.koc.user.application.port.in;

import com.koc.user.domain.token.TokenDto;

public interface CheckAccessTokenUseCase {
    TokenDto check(String accessToken, String email);
}
