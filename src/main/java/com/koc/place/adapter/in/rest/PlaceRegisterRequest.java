package com.koc.place.adapter.in.rest;

import com.koc.place.domain.Address;
import com.koc.place.domain.Place;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class PlaceRegisterRequest {
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String contact;
    @NotEmpty
    private final String url;
    @NotEmpty
    private final String description;

    @NotEmpty
    private final String postNo;
    @NotEmpty
    private final String street;
    @NotEmpty
    private final String parcel;
    @NotEmpty
    private final String detail;

    @NotNull
    private final Double longitude;
    @NotNull
    private final Double latitude;

    public Place toPlace() {
        Address address = new Address(postNo, street, parcel, detail, longitude, latitude);
        return new Place(null, name, contact, url, description, address);
    }
}
