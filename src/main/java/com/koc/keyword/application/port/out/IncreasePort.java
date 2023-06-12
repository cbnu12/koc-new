package com.koc.keyword.application.port.out;

import com.koc.keyword.domain.KeywordType;

public interface IncreasePort {
    void increase(KeywordType type, String keyword);
}
