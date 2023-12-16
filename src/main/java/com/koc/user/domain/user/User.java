package com.koc.user.domain.user;

import com.koc.user.domain.vo.*;

public class User {
    private Id id;
    private KocId kocId;
    private Password password;
    private RefreshToken refreshToken;
    private UserStatus userStatus;
    private Email email;

    public User(Long id, String kocId, String password, String refreshToken, UserStatus userStatus, String email) {
        this.id = new Id(id);
        this.kocId = new KocId(kocId);
        this.password = new Password(password);
        this.refreshToken = new RefreshToken(refreshToken);
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
                .password(password.value())
                .email(email.value())
                .userStatus(userStatus)
                .build();
    }
}
