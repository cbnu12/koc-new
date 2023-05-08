package com.koc.place.application.port.in;

import com.koc.place.domain.Place;
import org.springframework.data.domain.Page;

public interface SearchUseCase {
    Page<Place> execute(SearchQuery query);
}
