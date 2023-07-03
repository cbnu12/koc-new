package com.koc.keyword.application.service;

import com.koc.keyword.application.port.in.KeywordSearchQuery;
import com.koc.keyword.application.port.in.KeywordSearchUseCase;
import com.koc.keyword.application.port.out.KeywordSearchPort;
import com.koc.keyword.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordQueryService implements KeywordSearchUseCase {
    private final KeywordSearchPort keywordSearchPort;

    @Override
    public List<Keyword> search(final KeywordSearchQuery query) {
        return keywordSearchPort.search(query);
    }
}
