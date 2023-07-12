package com.koc.theme.application.port.in;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ThemePagingSearchQuery {
    private final Integer page;
    private final Integer size;
    private final String name;
    private final String description;

    public static ThemePagingSearchQuery create(Integer page, Integer size, String name, String description) {
        return new ThemePagingSearchQuery(page, size, name, description);
    }
}
