package com.koc.user.domain.user;

import com.koc.user.domain.vo.LoginType;
import com.koc.user.domain.vo.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String kocId;
    private String pw;
    private String refreshToken;
    private Long kakaoId;
    private String email;
    private LoginType loginType;
    private UserStatus userStatus;
}
