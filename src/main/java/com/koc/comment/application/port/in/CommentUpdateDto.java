package com.koc.comment.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateDto {
    private final Long contentId;
    private final String text;
}
