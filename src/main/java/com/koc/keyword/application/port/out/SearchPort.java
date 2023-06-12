package com.koc.keyword.application.port.out;

import com.koc.keyword.application.port.in.SearchQuery;
import com.koc.keyword.domain.Keyword;

import java.util.List;

public interface SearchPort {
    List<Keyword> search(SearchQuery query);
}
