package com.koc.comment.adapter.in.rest;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class CommentRequest {
    @NotEmpty
    private String text;
}
