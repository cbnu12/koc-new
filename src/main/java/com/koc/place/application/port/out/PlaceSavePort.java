package com.koc.place.application.port.out;

import com.koc.place.domain.Place;

public interface PlaceSavePort {
    Long save(Place place);
}
