package com.koc.keyword.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordLogRepository extends JpaRepository<KeywordLogJpaEntity, Long> {
}
