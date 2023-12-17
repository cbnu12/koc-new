package com.koc.user.domain.token;

import com.koc.user.domain.vo.Email;
import com.koc.user.domain.vo.Id;
import com.koc.user.domain.vo.RefreshToken;
import lombok.Getter;

@Getter
public class UserToken {
    private Id id;
    private Email email;
    private RefreshToken refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = new RefreshToken(refreshToken);
    }

    public UserToken(Long id, String email, String refreshToken) {
        this.id = new Id(id);
        this.email = new Email(email);
        this.refreshToken = new RefreshToken(refreshToken);
    }
}
