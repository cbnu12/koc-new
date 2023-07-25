package com.koc.theme.adapter.out.persistence;

import com.koc.theme.domain.Theme;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "theme")
@EntityListeners(AuditingEntityListener.class)
public class ThemeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @CreatedBy
    @Column(updatable = false)
    String createdBy;
    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;
    @LastModifiedBy
    String updatedBy;
    @LastModifiedDate
    LocalDateTime updatedAt;

    public static ThemeJpaEntity from(Theme theme) {
        return ThemeJpaEntity.builder()
                .id(theme.getId())
                .name(theme.getName())
                .description(theme.getDescription())
                .build();
    }

    public Theme toTheme() {
        return new Theme(id, name, description, null, createdBy, createdAt);
    }
}
