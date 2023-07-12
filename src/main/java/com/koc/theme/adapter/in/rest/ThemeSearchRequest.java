package com.koc.theme.adapter.in.rest;

import com.koc.theme.application.port.in.ThemePagingSearchQuery;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ThemeSearchRequest {
    @Min(value = 0)
    private final Integer page;

    @Min(value = 1)
    @Max(value = 50)
    private final Integer size;

    private final String name;
    private final String description;

    public ThemePagingSearchQuery toThemeSearchQuery() {
        return ThemePagingSearchQuery.create(page, size, name, description);
    }
}
