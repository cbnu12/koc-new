package com.koc.comment.application.port.in;

public interface CommentUpdateUseCase {
    Long update(Long id, CommentUpdateDto comment);
}
