package com.koc.comment.application.port.in;

import com.koc.comment.domain.CommentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentWriteDto {
    private final CommentType type;
    private final Long contentId;
    private final String text;
}
