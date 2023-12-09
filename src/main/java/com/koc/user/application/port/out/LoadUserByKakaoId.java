package com.koc.user.application.port.out;

import com.koc.user.domain.user.UserDto;

import java.util.Optional;

public interface LoadUserByKakaoId {
    Optional<UserDto> loadByKakaoId(Long kakaoId);
}
