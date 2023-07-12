package com.koc.theme.application.service;

import com.koc.theme.application.port.in.ThemePagingSearchQuery;
import com.koc.theme.application.port.in.ThemePagingSearchUseCase;
import com.koc.theme.application.port.out.ThemeSearchPort;
import com.koc.theme.domain.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ThemeQueryService implements ThemePagingSearchUseCase {
    private final ThemeSearchPort searchPort;

    @Override
    @Transactional(readOnly = true)
    public Page<Theme> search(ThemePagingSearchQuery query) {
        return searchPort.search(query.getPage(), query.getSize(), query.getName(), query.getDescription());
    }
}
