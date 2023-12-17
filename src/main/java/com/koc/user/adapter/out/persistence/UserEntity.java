package com.koc.user.adapter.out.persistence;

import com.koc.user.domain.vo.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users", schema = "koc")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Builder
    public UserEntity(Long id, String email, String password, UserStatus userStatus) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }
}
