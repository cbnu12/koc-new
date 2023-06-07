package com.koc.place.application.port.out;

import com.koc.place.domain.Place;

public interface RegisterPort {
    Long register(Place place);
}
