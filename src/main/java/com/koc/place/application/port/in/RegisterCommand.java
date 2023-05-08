package com.koc.place.application.port.in;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RegisterCommand {
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String contact;
    @NotEmpty
    private final String url;
    @NotEmpty
    private final List<String> category;
    @NotEmpty
    private final String description;

    @NotEmpty
    private final String postNo;
    @NotEmpty
    private final String street;
    @NotEmpty
    private final String parcel;
    @NotEmpty
    private final String detail;

    @NotNull
    private final Double longitude;
    @NotNull
    private final Double latitude;
}
