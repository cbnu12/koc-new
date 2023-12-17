package com.koc.user.adapter.in.rest;

public record SignUpRequest(
        String email,
        String password
) {
}
