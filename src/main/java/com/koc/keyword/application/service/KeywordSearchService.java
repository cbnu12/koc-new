package com.koc.keyword.application.service;

import com.koc.keyword.application.port.in.SearchQuery;
import com.koc.keyword.application.port.in.SearchUseCase;
import com.koc.keyword.application.port.out.SearchPort;
import com.koc.keyword.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordSearchService implements SearchUseCase {
    private final SearchPort searchPort;

    @Override
    public List<Keyword> search(final SearchQuery query) {
        return searchPort.search(query);
    }
}
