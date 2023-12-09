package com.koc.user.domain;

public record TokenDto(String refreshToken, String accessToken, String key) {
}
