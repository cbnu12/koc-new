package com.koc.place;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertThat(1 + 1).isEqualTo(2);
    }

}
