package com.koc.theme.domain;

import com.koc.place.domain.Place;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Theme {
    private Long id;
    private String name;
    private String description;

    private List<Place> places;

    private String createdBy;
    private LocalDateTime createdAt;
}
