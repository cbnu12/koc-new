package com.koc.keyword.adapter.out.persistence;

import com.koc.keyword.application.port.out.IncreasePort;
import com.koc.keyword.domain.KeywordType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class KeywordPersistenceAdapter implements IncreasePort {
    private final KeywordRepository repository;

    @Override
    public void increase(KeywordType type, String text) {
        Optional<KeywordJpaEntity> optionalEntity = repository.findByTypeAndText(type, text);
        KeywordJpaEntity entity = optionalEntity.orElse(KeywordJpaEntity.of(type, text));

        entity.increaseCount();
        repository.save(entity);
    }
}
