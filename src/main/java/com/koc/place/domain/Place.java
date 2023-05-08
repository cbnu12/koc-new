package com.koc.place.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Place {
    private final Long id;

    private final String name;
    private final String contact;
    private final String url;
    private final String description;
    private final String[] category;

    private final Address address;
}
