package com.koc.user.application.port.in;

import com.koc.user.domain.token.TokenDto;

import java.net.URISyntaxException;

public interface LoginUseCase {
    TokenDto login(String code) throws URISyntaxException;
}
