package com.koc.theme.application.port.out;

import com.koc.theme.domain.Theme;

public interface ThemeSavePort {
    Theme save(Theme theme);
}
