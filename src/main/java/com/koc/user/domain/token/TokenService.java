package com.koc.user.domain.token;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public TokenDto updateRefreshToken(TokenDto dto, String refreshToken) {
        var token = new UserToken(dto.id(), dto.refreshToken(), dto.key());
        token.updateRefreshToken(refreshToken);
        return new TokenDto(token.getId(), token.getRefreshToken(), null, token.getEmail());
    }
}
