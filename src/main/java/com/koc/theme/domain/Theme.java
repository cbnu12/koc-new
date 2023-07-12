package com.koc.theme.domain;

import com.koc.place.domain.Place;
import com.koc.theme.application.port.in.ThemeUpdateCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Theme {
    private Long id;
    private String name;
    private String description;

    private List<Place> places;

    private String createdBy;
    private LocalDateTime createdAt;

    public void update(ThemeUpdateCommand command) {
        name = command.getName();
        description = command.getDescription();
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public static Theme create(String name, String description) {
        return new Theme(null, name, description, null, null, null);
    }
}
