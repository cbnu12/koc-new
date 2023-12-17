package com.koc.user.domain.user;

import com.koc.common.exception.ValidationException;
import com.koc.user.domain.vo.Email;
import com.koc.user.domain.vo.Id;
import com.koc.user.domain.vo.Password;
import com.koc.user.domain.vo.UserStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private Id id;
    private Email email;
    private Password password;
    private UserStatus userStatus;

    @Builder
    public User(Long id, String email, String password, UserStatus userStatus) {
        this.id = new Id(id);
        this.password = new Password(password);
        this.userStatus = userStatus;
        this.email = new Email(email);
    }

    public static User create(String email, String password) throws ValidationException {
        var user = User.builder()
                .id(null)
                .email(email)
                .password(password)
                .userStatus(UserStatus.ACTIVE)
                .build();

        if(user.email.validate()) {
            throw new ValidationException(user.email.getClass().getSimpleName());
        }

        if(user.password.validate()) {
            throw new ValidationException(user.password.getClass().getSimpleName());
        }

        return user;
    }

    public void withdraw() {
        this.userStatus = UserStatus.INACTIVE;
    }
}
