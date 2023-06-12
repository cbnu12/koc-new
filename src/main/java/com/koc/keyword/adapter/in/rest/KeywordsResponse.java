package com.koc.keyword.adapter.in.rest;

import com.koc.keyword.domain.Keyword;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class KeywordsResponse {
    private final Integer size;
    private final List<Keyword> items;

    public static KeywordsResponse from(List<Keyword> keywords) {
        return new KeywordsResponse(keywords.size(), keywords);
    }
}
