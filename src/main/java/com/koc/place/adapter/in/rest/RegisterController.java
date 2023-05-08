package com.koc.place.adapter.in.rest;

import com.koc.place.application.port.in.RegisterCommand;
import com.koc.place.application.port.in.RegisterUseCase;
import com.koc.place.application.port.in.SearchQuery;
import com.koc.place.application.port.in.SearchUseCase;
import com.koc.place.domain.Place;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RegisterController {
    private final RegisterUseCase registerUseCase;
    private final SearchUseCase searchUseCase;

    @GetMapping("/place")
    public SearchPageResponse search(@Valid final SearchQuery query) {
        Page<Place> places = searchUseCase.execute(query);
        return SearchPageResponse.from(places);
    }

    @PostMapping("/place")
    public Long register(@RequestBody @Valid final RegisterCommand command) {
        return registerUseCase.execute(command);
    }
}
