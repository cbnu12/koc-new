package com.koc.theme.adapter.in.rest;

import com.koc.place.domain.Place;
import com.koc.theme.domain.Theme;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ThemeSearchResponse {
    private Long id;
    private String name;
    private String description;

    private List<Place> places;

    public static ThemeSearchResponse from(Theme theme) {
        return new ThemeSearchResponse(
                theme.getId(),
                theme.getName(),
                theme.getDescription(),
                theme.getPlaces()
        );
    }
}
