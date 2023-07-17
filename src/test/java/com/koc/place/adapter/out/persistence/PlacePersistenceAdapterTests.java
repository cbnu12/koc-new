package com.koc.place.adapter.out.persistence;

import com.koc.place.domain.Address;
import com.koc.place.domain.Place;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlacePersistenceAdapterTests {
    @Autowired
    private PlaceJpaRepository repository;

    @Test
    @DisplayName("장소 등록 테스트")
    void execute() {
        PlacePersistenceAdapter adapter = new PlacePersistenceAdapter(repository);
        Address address = new Address(
                "08324",
                "서울특별시 구로구 구일로 110",
                "서울특별시 구로구 구로동 642-108",
                "해원리버파크",
                123.0D,
                241.0D
        );
        Place command = new Place(
                null,
                "이디야커피 구일역점",
                "02-868-1816",
                "https://www.ediya.com",
                "구일역 근처 좌석 많은 카페. 위치가 위치인 만큼 사람들이 많지 않다. 대신 가는 것도 쉽지 않음,,, 😭",
                address
        );
        Long id = adapter.save(command);
        Assertions.assertThat(id).isEqualTo(1L);
    }
}
