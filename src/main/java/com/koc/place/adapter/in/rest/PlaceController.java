package com.koc.place.adapter.in.rest;

import com.koc.place.application.port.in.RegisterUseCase;
import com.koc.place.application.port.in.SearchQuery;
import com.koc.place.application.port.in.SearchUseCase;
import com.koc.place.domain.Place;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
class PlaceController {
    private final RegisterUseCase registerUseCase;
    private final SearchUseCase searchUseCase;

    @GetMapping("/places")
    public SearchPageResponse search(@Valid final SearchQuery query) {
        Page<Place> places = searchUseCase.search(query);
        return SearchPageResponse.from(places);
    }

    @GetMapping("/places/{id}")
    public SearchResponse searchById(@PathVariable(name = "id") final Long id) {
        Optional<Place> optionalPlace = searchUseCase.searchById(id);
        Place place = optionalPlace
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "[%s] 장소 조회 불가".formatted(id)));
        return SearchResponse.from(place);
    }

    @PostMapping("/places")
    public Long register(@RequestBody @Valid final RegisterRequest command) {
        return registerUseCase.register(command.toPlace());
    }
}
