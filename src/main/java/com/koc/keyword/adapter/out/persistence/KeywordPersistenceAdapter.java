package com.koc.keyword.adapter.out.persistence;

import com.koc.keyword.application.port.in.KeywordSearchQuery;
import com.koc.keyword.application.port.out.KeywordSavePort;
import com.koc.keyword.application.port.out.KeywordSearchPort;
import com.koc.keyword.domain.Keyword;
import com.koc.keyword.domain.KeywordType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class KeywordPersistenceAdapter implements KeywordSavePort, KeywordSearchPort {
    private final KeywordRepository repository;
    private final KeywordLogRepository logRepository;

    @Override
    @Transactional
    public Long save(Keyword keyword) {
        KeywordJpaEntity entity = repository.save(KeywordJpaEntity.of(keyword));
        logRepository.save(KeywordLogJpaEntity.of(entity));
        return entity.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Keyword> searchByTypeAndText(KeywordType type, String text) {
        return repository.findByTypeAndText(type, text).map(KeywordJpaEntity::toKeyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Keyword> search(KeywordSearchQuery query) {
        Page<KeywordJpaEntity> keywordEntities = repository.findByTypeOrderByCountDesc(query.getType(), Pageable.ofSize(query.getSize()));
        return keywordEntities.map(KeywordJpaEntity::toKeyword).getContent();
    }
}
