package com.koc.user.adapter.in.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDataResponse {
    private String refreshToken;
    private String acsessToken;
    private String key;
}
