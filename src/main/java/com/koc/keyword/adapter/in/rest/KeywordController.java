package com.koc.keyword.adapter.in.rest;

import com.koc.keyword.application.port.in.SearchQuery;
import com.koc.keyword.application.port.in.SearchUseCase;
import com.koc.keyword.domain.Keyword;
import com.koc.keyword.domain.KeywordType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
class KeywordController {
    private final SearchUseCase searchUseCase;

    @GetMapping("/places/popular/hits")
    public KeywordsResponse search(@Valid final KeywordSearchQuery query) {
        List<Keyword> keywords = searchUseCase.search(new SearchQuery(query.getSize(), KeywordType.PLACE));
        return KeywordsResponse.from(keywords);
    }
}
