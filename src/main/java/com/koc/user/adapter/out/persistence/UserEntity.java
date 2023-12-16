package com.koc.user.adapter.out.persistence;

import com.koc.user.domain.user.UserDto;
import com.koc.user.domain.vo.UserStatus;
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
    private String pw;
    private String refreshToken;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .password(pw)
                .refreshToken(refreshToken)
                .email(email)
                .userStatus(userStatus)
                .build();
    }
}
