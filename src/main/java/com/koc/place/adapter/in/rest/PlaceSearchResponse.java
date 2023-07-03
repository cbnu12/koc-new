package com.koc.place.adapter.in.rest;

import com.koc.place.domain.Place;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class PlaceSearchResponse {
    private final Long id;

    private final String name;
    private final String contact;
    private final String url;
    private final String description;

    private final String postNo;
    private final String street;
    private final String parcel;
    private final String detail;

    private final Double longitude;
    private final Double latitude;

    public static PlaceSearchResponse from(Place place) {
        return new PlaceSearchResponse(
                place.getId(),
                place.getName(),
                place.getContact(),
                place.getUrl(),
                place.getDescription(),
                place.getAddress().getPostNo(),
                place.getAddress().getStreet(),
                place.getAddress().getParcel(),
                place.getAddress().getDetail(),
                place.getAddress().getLongitude(),
                place.getAddress().getLatitude()
        );
    }
}
