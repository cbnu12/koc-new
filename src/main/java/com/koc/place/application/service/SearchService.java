package com.koc.place.application.service;

import com.koc.place.application.port.in.SearchQuery;
import com.koc.place.application.port.in.SearchUseCase;
import com.koc.place.application.port.out.KeywordCountUpPort;
import com.koc.place.application.port.out.SearchPort;
import com.koc.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchService implements SearchUseCase {
    private final SearchPort searchPort;
    private final KeywordCountUpPort keywordCountUpPort;

    @Override
    public Page<Place> search(SearchQuery query) {
        return searchPort.search(query);
    }

    @Override
    public Optional<Place> searchById(Long id) {
        Optional<Place> optionalPlace = searchPort.searchById(id);
        optionalPlace.ifPresent(place -> keywordCountUpPort.increaseCount(place.getId()));
        return optionalPlace;
    }
}
