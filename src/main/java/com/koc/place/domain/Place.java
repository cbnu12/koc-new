package com.koc.place.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class Place {
    private final Long id;

    private final String name;
    private final String contact;
    private final String url;
    private final String description;
    private final List<String> category;

    private final Address address;
}
