package com.koc.place.application.service;

import com.koc.keyword.application.port.in.KeywordSearchQuery;
import com.koc.keyword.application.port.out.KeywordSearchPort;
import com.koc.keyword.domain.Keyword;
import com.koc.keyword.domain.KeywordType;
import com.koc.place.application.port.in.PlaceSearchPopularUseCase;
import com.koc.place.application.port.in.PlaceSearchQuery;
import com.koc.place.application.port.in.PlaceSearchUseCase;
import com.koc.place.application.port.out.KeywordCountUpPort;
import com.koc.place.application.port.out.PlaceSearchPort;
import com.koc.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceQueryService implements PlaceSearchUseCase, PlaceSearchPopularUseCase {
    private final PlaceSearchPort placeSearchPort;
    private final KeywordCountUpPort keywordCountUpPort;
    private final KeywordSearchPort keywordSearchPort;

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

    @Override
    public Page<Place> searchTrend(Integer size) {
        KeywordSearchQuery keywordSearchQuery = KeywordSearchQuery.of(size, KeywordType.PLACE);
        List<Keyword> keywords = keywordSearchPort.search(keywordSearchQuery);

        List<Long> ids = keywords.stream().map(Keyword::getText).map(Long::parseLong).toList();
        Map<Long, Place> places = placeSearchPort.searchByIds(ids).stream()
                .collect(Collectors.toMap(Place::getId, arg -> arg));

        List<Place> results = keywords.stream()
                .filter(keyword -> places.containsKey(Long.parseLong(keyword.getText())))
                .map(keyword -> places.get(Long.parseLong(keyword.getText())))
                .toList();
        return new PageImpl<>(results, PageRequest.of(0, size), size);
    }

    @Override
    public Page<Place> searchRecommend(Integer size) {
        return null;
    }

    @Override
    public Page<Place> searchHighScore(Integer size) {
        return null;
    }
}
