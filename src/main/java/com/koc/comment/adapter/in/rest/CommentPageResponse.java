package com.koc.comment.adapter.in.rest;

import com.koc.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentPageResponse {
    private final Integer page;
    private final Integer size;
    private final Long total;
    private final List<CommentResponse> items;

    public static CommentPageResponse from(Page<Comment> comments) {
        return new CommentPageResponse(
                comments.getNumber(),
                comments.getSize(),
                comments.getTotalElements(),
                comments.map(CommentResponse::from).getContent()
        );
    }
}
