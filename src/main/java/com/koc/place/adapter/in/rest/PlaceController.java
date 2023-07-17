package com.koc.place.adapter.in.rest;

import com.koc.place.application.port.in.*;
import com.koc.place.domain.Place;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
class PlaceController {
    private final PlaceRegisterUseCase placeRegisterUseCase;
    private final PlaceSearchUseCase placeSearchUseCase;
    private final PlaceSearchPopularUseCase placeSearchPopularUseCase;

    @GetMapping("/places")
    public PlaceSearchPageResponse search(@Valid final PlaceSearchQuery query) {
        Page<Place> places;
        if (query.getSortBy() == null) {
            places = placeSearchUseCase.search(query);
        } else if (Objects.requireNonNull(query.getSortBy()) == PlaceSortType.TREND) {
            places = placeSearchPopularUseCase.searchTrend(query.getSize());
        } else {
            places = placeSearchUseCase.search(query);
        }
        return PlaceSearchPageResponse.from(places);
    }

    @GetMapping("/places/{id}")
    public PlaceSearchResponse searchById(@PathVariable(name = "id") final Long id) {
        Optional<Place> optionalPlace = placeSearchUseCase.searchById(id);
        Place place = optionalPlace
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "[%s] 장소 조회 불가".formatted(id)));
        return PlaceSearchResponse.from(place);
    }

    @PostMapping("/places")
    public Long register(@RequestBody @Valid final PlaceRegisterRequest command) {
        return placeRegisterUseCase.register(command.toPlace());
    }
}
