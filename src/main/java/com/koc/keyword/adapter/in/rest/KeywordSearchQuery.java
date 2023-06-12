package com.koc.keyword.adapter.in.rest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class KeywordSearchQuery {
    @Min(value = 1)
    @Max(value = 50)
    private final Integer size;
}
