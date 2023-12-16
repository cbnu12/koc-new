package com.koc.user.domain.user;

import com.koc.user.domain.vo.Email;
import com.koc.user.domain.vo.LoginType;
import com.koc.user.domain.vo.Password;
import com.koc.user.domain.vo.UserStatus;

public class User {
    private Long id;
    private String kocId;
    private Password password;
    private String refreshToken;
    private Long kakaoUserId;
    private LoginType loginType;
    private UserStatus userStatus;
    private Email email;

    public User(Long id, String kocId, String password, String refreshToken, Long kakaoUserId, LoginType loginType,
                UserStatus userStatus, String email) {
        this.id = id;
        this.kocId = kocId;
        this.password = new Password(password);
        this.refreshToken = refreshToken;
        this.kakaoUserId = kakaoUserId;
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
                .id(id)
                .kocId(kocId)
                .pw(password.value())
                .kakaoId(kakaoUserId)
                .email(email.value())
                .loginType(loginType)
                .userStatus(userStatus)
                .build();
    }
}
