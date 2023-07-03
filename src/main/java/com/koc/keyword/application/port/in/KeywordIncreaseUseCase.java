package com.koc.keyword.application.port.in;

import com.koc.keyword.domain.KeywordType;

public interface KeywordIncreaseUseCase {
    void increase(KeywordType type, String keyword);
}
