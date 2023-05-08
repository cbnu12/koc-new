package com.koc.place.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "place")
@EntityListeners(AuditingEntityListener.class)
class PlaceJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;
    private String url;
    private String description;
    @ElementCollection
    private List<String> category;

    @Embedded
    private AddressJpaEmbeddedEntity address;

    @CreatedBy
    String createdBy;
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedBy
    String updatedBy;
    @LastModifiedDate
    LocalDateTime updatedAt;
}
