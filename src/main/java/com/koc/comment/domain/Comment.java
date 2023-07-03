package com.koc.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Comment {
    private Long id;
    private Long contentId;
    private CommentType type;
    private String text;

    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;

    public void update(String text, Long updatedBy) {
        this.text = text;
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
    }

    public static Comment create(Long contentId, CommentType type, String text, Long userId) {
        return new Comment(null, contentId, type, text, userId, LocalDateTime.now(), userId, LocalDateTime.now());
    }
}
