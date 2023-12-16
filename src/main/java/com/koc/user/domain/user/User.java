package com.koc.user.domain.user;

import com.koc.user.domain.vo.*;
import lombok.Builder;

@Builder
public class User {
    private Id id;
    private Email email;
    private Password password;
    private RefreshToken refreshToken;
    private UserStatus userStatus;

    public User(Long id, String password, String refreshToken, UserStatus userStatus, String email) {
        this.id = new Id(id);
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
                .password(password.value())
                .email(email.value())
                .userStatus(userStatus)
                .build();
    }
}
