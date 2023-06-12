package com.koc.keyword.application.port.in;

import com.koc.keyword.domain.KeywordType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchQuery {
    private final Integer size;
    private final KeywordType type;
}
