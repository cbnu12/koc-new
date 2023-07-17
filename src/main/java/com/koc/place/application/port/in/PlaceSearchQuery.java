package com.koc.place.application.port.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceSearchQuery {
    @Min(value = 0)
    private final Integer page;

    @Min(value = 1)
    @Max(value = 50)
    private final Integer size;

    private final PlaceSortType sortBy;
}
