package com.koc.comment.adapter.in.rest;

import com.koc.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
class CommentResponse {
    private final String text;

    private final Long createdBy;
    private final LocalDateTime createdAt;
    private final Long updatedBy;
    private final LocalDateTime updatedAt;

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getText(),
                comment.getCreatedBy(),
                comment.getCreatedAt(),
                comment.getUpdatedBy(),
                comment.getUpdatedAt()
        );
    }
}
