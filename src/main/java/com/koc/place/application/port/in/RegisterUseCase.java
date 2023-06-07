package com.koc.place.application.port.in;


import com.koc.place.domain.Place;

public interface RegisterUseCase {
    Long register(Place place);
}
