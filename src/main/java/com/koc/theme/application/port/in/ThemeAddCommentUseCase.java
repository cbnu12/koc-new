package com.koc.theme.application.port.in;

import com.koc.comment.domain.Comment;
import com.koc.theme.domain.Theme;

public interface ThemeAddCommentUseCase {
    void addComment(Theme theme, Comment comment);
}
