package com.koc.place.application.service;

import com.koc.place.application.port.in.RegisterUseCase;
import com.koc.place.application.port.out.RegisterPort;
import com.koc.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {
    private final RegisterPort registerPort;

    @Transactional
    public Long register(Place place) {
        return registerPort.register(place);
    }
}
