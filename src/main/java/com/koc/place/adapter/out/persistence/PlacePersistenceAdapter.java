package com.koc.place.adapter.out.persistence;

import com.koc.place.application.port.in.SearchQuery;
import com.koc.place.application.port.out.RegisterPort;
import com.koc.place.application.port.out.SearchPort;
import com.koc.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class PlacePersistenceAdapter implements RegisterPort, SearchPort {
    private final PlaceJpaRepository repository;

    @Override
    public Long register(Place place) {
        PlaceJpaEntity inserted = repository.save(commandToEntity(place));
        return inserted.getId();
    }

    @Override
    public Page<Place> search(SearchQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        Page<PlaceJpaEntity> entities = repository.findAll(pageable);
        return entities.map(PlaceJpaEntity::toPlace);
    }

    @Override
    public Optional<Place> searchById(Long id) {
        return repository.findById(id).map(PlaceJpaEntity::toPlace);
    }

    private PlaceJpaEntity commandToEntity(Place place) {
        AddressJpaEmbeddedEntity addressEntity = AddressJpaEmbeddedEntity.builder()
                .postNo(place.getAddress().getPostNo())
                .street(place.getAddress().getStreet())
                .parcel(place.getAddress().getParcel())
                .detail(place.getAddress().getDetail())
                .longitude(place.getAddress().getLongitude())
                .latitude(place.getAddress().getLatitude())
                .build();

        return PlaceJpaEntity.builder()
                .name(place.getName())
                .contact(place.getContact())
                .url(place.getUrl())
                .description(place.getDescription())
                .address(addressEntity)
                .build();
    }
}
