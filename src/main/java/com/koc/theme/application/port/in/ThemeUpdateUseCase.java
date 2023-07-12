package com.koc.theme.application.port.in;

public interface ThemeUpdateUseCase {
    Long update(Long id, ThemeUpdateCommand theme);
}
