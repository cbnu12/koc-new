package com.koc.place.application.port.out;

import com.koc.place.application.port.in.PlaceSearchQuery;
import com.koc.place.domain.Place;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PlaceSearchPort {
    Page<Place> search(PlaceSearchQuery query);

    Optional<Place> searchById(Long id);
}
