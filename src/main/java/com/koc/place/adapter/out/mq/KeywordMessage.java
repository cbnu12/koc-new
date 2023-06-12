package com.koc.place.adapter.out.mq;

import com.koc.keyword.domain.KeywordType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class KeywordMessage {
    private final KeywordType type;
    private final String text;
}
