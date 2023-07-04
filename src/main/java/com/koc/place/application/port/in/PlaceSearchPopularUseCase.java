package com.koc.place.application.port.in;

import com.koc.place.domain.Place;

import java.util.List;

public interface PlaceSearchPopularUseCase {
    List<Place> searchPopularByKeyword();

    List<Place> searchPopularByComment();

    List<Place> searchPopularByScore();
}