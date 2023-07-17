package com.koc.keyword.application.port.in;

import com.koc.keyword.domain.KeywordType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KeywordSearchQuery {
    private final Integer size;
    private final KeywordType type;

    public static KeywordSearchQuery of(Integer size, KeywordType type) {
        return new KeywordSearchQuery(size, type);
    }
}
