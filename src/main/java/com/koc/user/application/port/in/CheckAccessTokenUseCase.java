package com.koc.user.application.port.in;

import com.koc.user.domain.TokenDto;

public interface CheckAccessTokenUseCase {
    TokenDto check(String accessToken, String email);
}
