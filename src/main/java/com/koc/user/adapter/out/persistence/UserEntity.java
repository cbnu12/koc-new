package com.koc.user.adapter.out.persistence;

import com.koc.user.domain.user.UserDto;
import com.koc.user.domain.vo.LoginType;
import com.koc.user.domain.vo.UserStatus;
import com.koc.user.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "koc")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String kocId;
    private String pw;
    private String refreshToken;
    private Long kakaoId;
    private String email;
    @Enumerated(EnumType.STRING)
    private LoginType loginType;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .kocId(kocId)
                .pw(pw)
                .refreshToken(refreshToken)
                .kakaoId(kakaoId)
                .email(email)
                .loginType(loginType)
                .userStatus(userStatus)
                .build();
    }
}
