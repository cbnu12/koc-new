package com.koc.keyword.adapter.out.persistence;

import com.koc.keyword.application.port.in.SearchQuery;
import com.koc.keyword.application.port.out.IncreasePort;
import com.koc.keyword.application.port.out.SearchPort;
import com.koc.keyword.domain.Keyword;
import com.koc.keyword.domain.KeywordType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class KeywordPersistenceAdapter implements IncreasePort, SearchPort {
    private final KeywordRepository repository;

    @Override
    public void increase(KeywordType type, String text) {
        Optional<KeywordJpaEntity> optionalEntity = repository.findByTypeAndText(type, text);
        KeywordJpaEntity entity = optionalEntity.orElse(KeywordJpaEntity.of(type, text));

        entity.increaseCount();
        repository.save(entity);
    }

    @Override
    public List<Keyword> search(SearchQuery query) {
        Page<KeywordJpaEntity> keywordEntities = repository.findByTypeOrderByCountDesc(query.getType(), Pageable.ofSize(query.getSize()));
        return keywordEntities.map(KeywordJpaEntity::toKeyword).getContent();
    }
}
