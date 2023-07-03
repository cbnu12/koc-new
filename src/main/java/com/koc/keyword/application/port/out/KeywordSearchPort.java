package com.koc.keyword.application.port.out;

import com.koc.keyword.application.port.in.KeywordSearchQuery;
import com.koc.keyword.domain.Keyword;
import com.koc.keyword.domain.KeywordType;

import java.util.List;
import java.util.Optional;

public interface KeywordSearchPort {
    List<Keyword> search(KeywordSearchQuery query);

    Optional<Keyword> searchByTypeAndText(KeywordType type, String text);
}
