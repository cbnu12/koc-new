package com.koc.place.application.port.in;

import com.koc.comment.domain.Comment;
import com.koc.place.domain.Place;

public interface PlaceAddCommentUseCase {
    void addComment(Place place, Comment comment);
}
