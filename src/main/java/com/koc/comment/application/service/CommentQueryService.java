package com.koc.comment.application.service;

import com.koc.comment.application.port.in.CommentSearchUseCase;
import com.koc.comment.application.port.out.CommentSearchPort;
import com.koc.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentQueryService implements CommentSearchUseCase {
    private final CommentSearchPort searchPort;

    @Override
    public Page<Comment> search(Integer page, Integer size, Long contentId) {
        return searchPort.search(page, size);
    }
}
