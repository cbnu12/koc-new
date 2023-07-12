package com.koc.theme.application.port.in;

import com.koc.theme.domain.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThemeCreateCommand {
    private final String name;
    private final String description;

    public Theme toTheme() {
        return Theme.create(name, description);
    }
}
