package com.koc.comment.adapter.out.persistence;

import com.koc.comment.domain.Comment;
import com.koc.comment.domain.CommentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
class CommentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long contentId;

    @Enumerated(EnumType.STRING)
    private CommentType type;
    private String text;

    @CreatedBy
    Long createdBy;
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedBy
    Long updatedBy;
    @LastModifiedDate
    LocalDateTime updatedAt;

    public Comment toComment() {
        return new Comment(id, contentId, type, text, createdBy, createdAt, updatedBy, updatedAt);
    }

    public static CommentJpaEntity from(Comment comment) {
        return new CommentJpaEntity(
                comment.getId(),
                comment.getContentId(),
                comment.getType(),
                comment.getText(),
                comment.getCreatedBy(),
                comment.getCreatedAt(),
                comment.getUpdatedBy(),
                comment.getUpdatedAt()
        );
    }
}
