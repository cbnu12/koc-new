package com.koc.theme.application.port.in;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThemeUpdateCommand {
    private final String name;
    private final String description;

}
