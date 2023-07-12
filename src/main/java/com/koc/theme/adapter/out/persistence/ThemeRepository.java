package com.koc.theme.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<ThemeJpaEntity, Long> {
    Page<ThemeJpaEntity> findByNameLikeAndDescriptionLike(String name, String description, Pageable pageable);
}
