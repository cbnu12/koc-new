package com.koc.user.adapter.in.rest;

public record CheckAccessTokenRequest(
        String token,
        String email
) {
}
