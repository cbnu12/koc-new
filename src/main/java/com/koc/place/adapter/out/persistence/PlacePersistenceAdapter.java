package com.koc.place.adapter.out.persistence;

import com.koc.place.application.port.in.RegisterCommand;
import com.koc.place.application.port.out.RegisterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PlacePersistenceAdapter implements RegisterPort {
    private final PlaceJpaRepository repository;

    @Override
    public Long execute(RegisterCommand command) {
        PlaceJpaEntity inserted = repository.save(commandToEntity(command));
        return inserted.getId();
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
