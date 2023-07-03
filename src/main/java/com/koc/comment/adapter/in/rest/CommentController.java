package com.koc.comment.adapter.in.rest;

import com.koc.comment.application.port.in.*;
import com.koc.comment.domain.Comment;
import com.koc.comment.domain.CommentType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentWriteUseCase writeUseCase;
    private final CommentUpdateUseCase updateUseCase;
    private final CommentSearchUseCase searchUseCase;

    @GetMapping("/places/{placeId}/comments")
    public CommentPageResponse findPlaceComments(
            @PathVariable(name = "placeId") final Long placeId,
            @Valid final CommentSearchQuery query
    ) {
        Page<Comment> comments = searchUseCase.search(query.getPage(), query.getSize(), placeId);
        return CommentPageResponse.from(comments);
    }

    @PostMapping("/places/{placeId}/comments")
    public Long write(
            @PathVariable(name = "placeId") final Long placeId,
            @RequestBody @Valid final CommentRequest request
    ) {
        CommentWriteDto dto = new CommentWriteDto(CommentType.PLACE, placeId, request.getText());
        return writeUseCase.write(dto);
    }

    @PatchMapping("/places/{placeId}/comments/{id}")
    public Long update(
            @PathVariable(name = "placeId") final Long placeId,
            @PathVariable(name = "id") final Long id,
            @RequestBody @Valid final CommentRequest request
    ) {
        CommentUpdateDto dto = new CommentUpdateDto(placeId, request.getText());
        return updateUseCase.update(id, dto);
    }
}
