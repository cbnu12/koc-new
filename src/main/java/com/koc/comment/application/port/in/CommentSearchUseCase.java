package com.koc.comment.application.port.in;

import com.koc.comment.domain.Comment;
import org.springframework.data.domain.Page;

public interface CommentSearchUseCase {
    Page<Comment> search(Integer page, Integer size, Long contentId);
}
