package com.koc.place.application.port.in;


import com.koc.place.domain.Place;

public interface PlaceRegisterUseCase {
    Long register(Place place);
}
