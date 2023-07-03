package com.koc.keyword.application.port.in;

import com.koc.keyword.domain.Keyword;

import java.util.List;

public interface KeywordSearchUseCase {
    List<Keyword> search(KeywordSearchQuery query);
}
