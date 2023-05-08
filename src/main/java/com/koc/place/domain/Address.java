package com.koc.place.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {
    private final String postNo;
    private final String street;
    private final String parcel;
    private final String detail;

    private final Double longitude;
    private final Double latitude;
}
