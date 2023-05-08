package com.koc.place.application.port.out;

import com.koc.place.application.port.in.SearchQuery;
import com.koc.place.domain.Place;
import org.springframework.data.domain.Page;

public interface SearchPort {
    Page<Place> execute(SearchQuery query);
}
