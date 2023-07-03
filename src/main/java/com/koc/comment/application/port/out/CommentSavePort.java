package com.koc.comment.application.port.out;

import com.koc.comment.domain.Comment;

public interface CommentSavePort {
    Long save(Comment comment);
}
