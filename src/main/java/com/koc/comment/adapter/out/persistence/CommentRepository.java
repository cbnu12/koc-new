package com.koc.comment.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository<CommentJpaEntity, Long> {
}
