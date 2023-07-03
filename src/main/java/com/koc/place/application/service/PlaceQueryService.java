package com.koc.place.application.service;

import com.koc.place.application.port.in.PlaceSearchQuery;
import com.koc.place.application.port.in.PlaceSearchUseCase;
import com.koc.place.application.port.out.KeywordCountUpPort;
import com.koc.place.application.port.out.PlaceSearchPort;
import com.koc.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceQueryService implements PlaceSearchUseCase {
    private final PlaceSearchPort placeSearchPort;
    private final KeywordCountUpPort keywordCountUpPort;

    @Override
    public Page<Place> search(PlaceSearchQuery query) {
        return placeSearchPort.search(query);
    }

    @Override
    public Optional<Place> searchById(Long id) {
        Optional<Place> optionalPlace = placeSearchPort.searchById(id);
        optionalPlace.ifPresent(place -> keywordCountUpPort.increaseCount(place.getId()));
        return optionalPlace;
    }
}
