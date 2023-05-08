package com.koc.place.adapter.in.rest;

import com.koc.place.application.port.in.RegisterCommand;
import com.koc.place.application.port.in.RegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterUseCase registerUseCase;

    @PostMapping("/place")
    public Long execute(@RequestBody final RegisterCommand command) {
        return registerUseCase.execute(command);
    }
}
