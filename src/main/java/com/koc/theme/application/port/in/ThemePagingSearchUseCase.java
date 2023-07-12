package com.koc.theme.application.port.in;

import com.koc.theme.domain.Theme;
import org.springframework.data.domain.Page;

public interface ThemePagingSearchUseCase {
    Page<Theme> search(ThemePagingSearchQuery query);
}
