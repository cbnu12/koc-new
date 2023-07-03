package com.koc.place.application.port.in;

import com.koc.place.domain.Place;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PlaceSearchUseCase {
    Page<Place> search(PlaceSearchQuery query);

    Optional<Place> searchById(Long id);
}
