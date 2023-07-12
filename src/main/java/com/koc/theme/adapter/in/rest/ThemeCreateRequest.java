package com.koc.theme.adapter.in.rest;

import com.koc.theme.application.port.in.ThemeCreateCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ThemeCreateRequest {
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String description;

    public ThemeCreateCommand toCommand() {
        return new ThemeCreateCommand(name, description);
    }
}
