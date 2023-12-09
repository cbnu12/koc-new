package com.koc.user.adapter.in.rest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class TokenCheckResponse {
    private final String assessToken;
}
