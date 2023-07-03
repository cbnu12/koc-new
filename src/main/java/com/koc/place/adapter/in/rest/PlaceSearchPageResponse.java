package com.koc.place.adapter.in.rest;

import com.koc.place.domain.Place;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class PlaceSearchPageResponse {
    private final Integer page;
    private final Integer size;
    private final Long total;
    private final List<PlaceSearchResponse> items;

    public static PlaceSearchPageResponse from(Page<Place> places) {
        return new PlaceSearchPageResponse(
                places.getNumber(),
                places.getSize(),
                places.getTotalElements(),
                places.map(PlaceSearchResponse::from).getContent()
        );
    }
}
