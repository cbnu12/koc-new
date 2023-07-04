package com.koc.theme.application.port.in;

import com.koc.theme.domain.Theme;

import java.util.List;

public interface ThemeSearchUseCase {
    List<Theme> search(ThemeSearchQuery query);

    Theme searchById(Long id);
}
