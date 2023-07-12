package com.koc.theme.adapter.in.rest;

import com.koc.theme.application.port.in.ThemeUpdateCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ThemeUpdateRequest {
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String description;

    public ThemeUpdateCommand toCommand() {
        return new ThemeUpdateCommand(name, description);
    }
}
