package com.koc.place.application.port.out;

import com.koc.place.application.port.in.SearchQuery;
import com.koc.place.domain.Place;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SearchPort {
    Page<Place> search(SearchQuery query);

    Optional<Place> searchById(Long id);
}
