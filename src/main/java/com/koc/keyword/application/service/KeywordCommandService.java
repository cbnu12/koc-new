package com.koc.keyword.application.service;

import com.koc.keyword.application.port.in.KeywordIncreaseUseCase;
import com.koc.keyword.application.port.out.KeywordSavePort;
import com.koc.keyword.application.port.out.KeywordSearchPort;
import com.koc.keyword.domain.Keyword;
import com.koc.keyword.domain.KeywordType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordCommandService implements KeywordIncreaseUseCase {
    private final KeywordSavePort keywordSavePort;
    private final KeywordSearchPort keywordSearchPort;

    @Override
    public void increase(KeywordType type, String text) {
        Keyword keyword = keywordSearchPort.searchByTypeAndText(type, text).orElse(Keyword.create(type, text));
        keyword.increase();
        keywordSavePort.save(keyword);
    }
}
