package com.koc.user.domain.token;

public record TokenDto(Long id, String refreshToken, String accessToken, String key) {
}
