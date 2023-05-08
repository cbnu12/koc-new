package com.koc.place.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface PlaceJpaRepository extends JpaRepository<PlaceJpaEntity, Long> {
}
