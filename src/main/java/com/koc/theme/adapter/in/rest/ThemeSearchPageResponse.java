package com.koc.theme.adapter.in.rest;

import com.koc.theme.domain.Theme;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ThemeSearchPageResponse {
    private final Integer page;
    private final Integer size;
    private final Long total;
    private final List<ThemeSearchResponse> items;

    public static ThemeSearchPageResponse from(Page<Theme> themes) {
        return new ThemeSearchPageResponse(
                themes.getNumber(),
                themes.getSize(),
                themes.getTotalElements(),
                themes.map(ThemeSearchResponse::from).getContent()
        );
    }
}
