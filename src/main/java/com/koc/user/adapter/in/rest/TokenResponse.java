package com.koc.user.adapter.in.rest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class TokenResponse {
    private final String refreshToken;
    private final String accessToken;
    private final String key;
}
