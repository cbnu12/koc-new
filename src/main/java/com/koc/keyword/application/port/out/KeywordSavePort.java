package com.koc.keyword.application.port.out;

import com.koc.keyword.domain.Keyword;

public interface KeywordSavePort {
    Long save(Keyword keyword);
}
