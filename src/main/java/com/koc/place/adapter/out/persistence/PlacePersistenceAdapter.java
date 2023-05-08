package com.koc.place.adapter.out.persistence;

import com.koc.place.application.port.in.RegisterCommand;
import com.koc.place.application.port.in.SearchQuery;
import com.koc.place.application.port.out.RegisterPort;
import com.koc.place.application.port.out.SearchPort;
import com.koc.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PlacePersistenceAdapter implements RegisterPort, SearchPort {
    private final PlaceJpaRepository repository;

    @Override
    public Long execute(RegisterCommand command) {
        PlaceJpaEntity inserted = repository.save(commandToEntity(command));
        return inserted.getId();
    }

    @Override
    public Page<Place> execute(SearchQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        Page<PlaceJpaEntity> entities = repository.findAll(pageable);
        return entities.map(PlaceJpaEntity::toPlace);
    }

    private PlaceJpaEntity commandToEntity(RegisterCommand command) {
        AddressJpaEmbeddedEntity addressEntity = AddressJpaEmbeddedEntity.builder()
                .postNo(command.getPostNo())
                .street(command.getStreet())
                .parcel(command.getParcel())
                .detail(command.getDetail())
                .longitude(command.getLongitude())
                .latitude(command.getLatitude())
                .build();

        return PlaceJpaEntity.builder()
                .name(command.getName())
                .contact(command.getContact())
                .url(command.getUrl())
                .category(command.getCategory())
                .description(command.getDescription())
                .address(addressEntity)
                .build();
    }
}
