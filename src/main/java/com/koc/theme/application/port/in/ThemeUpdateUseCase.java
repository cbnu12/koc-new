package com.koc.theme.application.port.in;

import com.koc.theme.domain.Theme;

public interface ThemeUpdateUseCase {
    void update(Long id, Theme theme);
}
