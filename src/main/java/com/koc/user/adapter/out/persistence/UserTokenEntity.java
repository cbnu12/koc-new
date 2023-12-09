package com.koc.user.adapter.out.persistence;

import com.koc.user.domain.token.UserToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_token", schema = "koc")
public class UserTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String refreshToken;

    public UserToken toUserToken() {
        return UserToken.builder()
                .id(id)
                .email(email)
                .refreshToken(refreshToken)
                .build();
    }
}
