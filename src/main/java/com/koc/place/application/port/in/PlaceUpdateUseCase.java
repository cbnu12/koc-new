package com.koc.place.application.port.in;

import com.koc.place.domain.Place;

public interface PlaceUpdateUseCase {
    void update(Long id, Place place);
}
