package com.koc.place.application.service;

import com.koc.place.application.port.in.PlaceRegisterUseCase;
import com.koc.place.application.port.out.PlaceSavePort;
import com.koc.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceCommandService implements PlaceRegisterUseCase {
    private final PlaceSavePort placeSavePort;

    @Override
    @Transactional
    public Long register(Place place) {
        return placeSavePort.save(place);
    }
}
