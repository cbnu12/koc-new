package com.koc.place.application.service;

import com.koc.place.application.port.in.SearchQuery;
import com.koc.place.application.port.in.SearchUseCase;
import com.koc.place.application.port.out.SearchPort;
import com.koc.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService implements SearchUseCase {
    private final SearchPort searchPort;

    @Override
    public Page<Place> search(SearchQuery query) {
        return searchPort.search(query);
    }
}
