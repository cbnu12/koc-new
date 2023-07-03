package com.koc.keyword.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Keyword {
    private Long id;
    private KeywordType type;
    private String text;
    private Long count;

    public void increase() {
        count += 1;
    }

    public static Keyword create(KeywordType type, String text) {
        return new Keyword(null, type, text, 0L);
    }
}
