package com.koc.place.application.service;

import com.koc.place.application.port.in.RegisterCommand;
import com.koc.place.application.port.in.RegisterUseCase;
import com.koc.place.application.port.out.RegisterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {
    private final RegisterPort registerPort;

    @Override
    @Transactional
    public Long execute(RegisterCommand command) {
        return registerPort.execute(command);
    }
}
