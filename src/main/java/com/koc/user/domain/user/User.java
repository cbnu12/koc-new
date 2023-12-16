package com.koc.user.domain.user;

import com.koc.user.domain.vo.KakaoUserId;
import com.koc.user.domain.vo.KocId;
import com.koc.user.domain.vo.*;

public class User {
    private Id id;
    private KocId kocId;
    private Password password;
    private RefreshToken refreshToken;
    private KakaoUserId kakaoUserId;
    private LoginType loginType;
    private UserStatus userStatus;
    private Email email;

    public User(Long id, String kocId, String password, String refreshToken, Long kakaoUserId, LoginType loginType,
                UserStatus userStatus, String email) {
        this.id = new Id(id);
        this.kocId = new KocId(kocId);
        this.password = new Password(password);
        this.refreshToken = new RefreshToken(refreshToken);
        this.kakaoUserId = new KakaoUserId(kakaoUserId);
        this.loginType = loginType;
        this.userStatus = userStatus;
        this.email = new Email(email);
    }

    public void withdraw() {
        this.userStatus = UserStatus.INACTIVE;
    }

    public UserDto toDto() {
        return UserDto
                .builder()
                .id(id.value())
                .kocId(kocId.value())
                .pw(password.value())
                .kakaoId(kakaoUserId.value())
                .email(email.value())
                .loginType(loginType)
                .userStatus(userStatus)
                .build();
    }
}
