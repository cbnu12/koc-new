package com.koc.keyword.application.service;

import com.koc.keyword.application.port.in.IncreaseUseCase;
import com.koc.keyword.application.port.out.IncreasePort;
import com.koc.keyword.domain.KeywordType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordService implements IncreaseUseCase {
    private final IncreasePort increasePort;

    @Override
    public void increase(KeywordType type, String keyword) {
        increasePort.increase(type, keyword);
    }
}
