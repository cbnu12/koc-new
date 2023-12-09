package com.koc.user.domain.user;

import com.koc.user.adapter.out.persistence.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private Long id;
    private String kocId;
    private String pw;
    private String refreshToken;
    private Long kakaoUserId;
    private LoginType loginType;
    private UserStatus userStatus;
    private String email;


    public LoginType isKakaoLogin() {
        return LoginType.KAKAO;
    }

    public boolean isNomalUser() {
        return this.userStatus == UserStatus.ACTIVE;
    }

    public void withdraw() {
        this.userStatus = UserStatus.INACTIVE;
    }


    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .kocId(kocId)
                .pw(pw)
                .refreshToken(refreshToken)
                .kakaoId(kakaoUserId)
                .email(email)
                .loginType(loginType)
                .userStatus(userStatus)
                .build();
    }

    public UserDto toDto() {
        return UserDto
                .builder()
                .id(id)
                .kocId(kocId)
                .pw(pw)
                .kakaoId(kakaoUserId)
                .email(email)
                .loginType(loginType)
                .userStatus(userStatus)
                .build();
    }
}
