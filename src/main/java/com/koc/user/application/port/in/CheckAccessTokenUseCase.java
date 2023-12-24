package com.koc.user.application.port.in;

public interface CheckAccessTokenUseCase {
    void check(String accessToken, String email);
}
