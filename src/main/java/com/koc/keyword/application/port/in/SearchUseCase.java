package com.koc.keyword.application.port.in;

import com.koc.keyword.domain.Keyword;

import java.util.List;

public interface SearchUseCase {
    List<Keyword> search(SearchQuery query);
}
