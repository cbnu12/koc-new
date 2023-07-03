package com.koc.comment.adapter.out.persistence;

import com.koc.comment.application.port.out.CommentSavePort;
import com.koc.comment.application.port.out.CommentSearchPort;
import com.koc.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class CommentPersistenceAdapter implements CommentSavePort, CommentSearchPort {
    private final CommentRepository repository;

    @Override
    public Long save(Comment comment) {
        CommentJpaEntity entity = comment.getId() != null
                ? repository.findById(comment.getId()).orElseThrow()
                : CommentJpaEntity.from(comment);
        CommentJpaEntity result = repository.save(entity);
        return result.getId();
    }

    @Override
    public Page<Comment> search(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size)).map(CommentJpaEntity::toComment);
    }

    @Override
    public Optional<Comment> searchById(Long id) {
        Optional<CommentJpaEntity> entity = repository.findById(id);
        return entity.map(CommentJpaEntity::toComment);
    }
}
