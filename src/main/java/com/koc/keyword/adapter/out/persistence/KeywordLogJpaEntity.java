package com.koc.keyword.adapter.out.persistence;

import com.koc.keyword.domain.KeywordType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "keyword_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class KeywordLogJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Long id;
    @Enumerated(EnumType.STRING)
    private KeywordType type;
    private String text;

    @CreatedBy
    @Column(updatable = false)
    String createdBy;
    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;

    public static KeywordLogJpaEntity of(KeywordJpaEntity keyword) {
        KeywordLogJpaEntity logEntity = new KeywordLogJpaEntity();
        logEntity.id = keyword.getId();
        logEntity.type = keyword.getType();
        logEntity.text = keyword.getText();
        return logEntity;
    }
}
