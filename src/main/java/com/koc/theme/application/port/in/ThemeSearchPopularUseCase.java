package com.koc.theme.application.port.in;

import com.koc.theme.domain.Theme;

import java.util.List;

public interface ThemeSearchPopularUseCase {
    List<Theme> searchPopularByKeyword();

    List<Theme> searchPopularByComment();

    List<Theme> searchPopularByScore();
}
