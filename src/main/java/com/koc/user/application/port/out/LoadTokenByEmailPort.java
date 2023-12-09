package com.koc.user.application.port.out;

import com.koc.user.domain.token.TokenDto;

import java.util.Optional;

public interface LoadTokenByEmailPort {
    Optional<TokenDto> load(String email);
}
