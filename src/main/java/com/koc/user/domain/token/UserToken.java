package com.koc.user.domain.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserToken {
    private Long id;
    private String email;
    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
