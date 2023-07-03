package com.koc.comment.application.port.out;

import com.koc.comment.domain.Comment;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CommentSearchPort {
    Page<Comment> search(Integer page, Integer size);

    Optional<Comment> searchById(Long id);
}
