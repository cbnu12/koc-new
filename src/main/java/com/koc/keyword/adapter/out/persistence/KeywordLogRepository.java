package com.koc.keyword.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface KeywordLogRepository extends JpaRepository<KeywordLogJpaEntity, Long> {
}
