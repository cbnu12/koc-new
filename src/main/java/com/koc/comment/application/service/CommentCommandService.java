package com.koc.comment.application.service;

import com.koc.comment.application.port.in.CommentUpdateDto;
import com.koc.comment.application.port.in.CommentUpdateUseCase;
import com.koc.comment.application.port.in.CommentWriteDto;
import com.koc.comment.application.port.in.CommentWriteUseCase;
import com.koc.comment.application.port.out.CommentSavePort;
import com.koc.comment.application.port.out.CommentSearchPort;
import com.koc.comment.domain.Comment;
import com.koc.core.KocHttpReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentCommandService implements CommentWriteUseCase, CommentUpdateUseCase {
    private final KocHttpReader httpReader;

    private final CommentSavePort savePort;
    private final CommentSearchPort searchPort;

    @Override
    @Transactional
    public Long write(CommentWriteDto dto) {
        Long userId = httpReader.findUserId().orElse(-1L);
        Comment comment = Comment.create(dto.getContentId(), dto.getType(), dto.getText(), userId);
        return savePort.save(comment);
    }

    @Override
    public Long update(Long id, CommentUpdateDto dto) {
        Long userId = httpReader.findUserId().orElse(-1L);
        Comment comment = searchPort.searchById(id).orElseThrow();
        comment.update(dto.getText(), userId);
        return savePort.save(comment);
    }
}
