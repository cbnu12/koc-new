package com.koc.user.adapter.out.api.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoToken {
    private final String tokenType;
    private final String accessToken;
    private final String refreshToken;
    private final Long expiresIn;
    private final Long refreshTokenExpiresIn;
}
