package com.koc.theme.application.port.out;

import com.koc.theme.domain.Theme;
import org.springframework.data.domain.Page;

public interface ThemeSearchPort {
    Page<Theme> search(Integer page, Integer size, String name, String description);

    Theme searchById(Long id);
}
