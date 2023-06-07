package com.koc.place.adapter.in.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
@Transactional
class PlaceControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private String registerCommandToJsonString(RegisterRequest command) throws JsonProcessingException {
        return mapper.writeValueAsString(command);
    }

    private RegisterRequest createMockRequestData() {
        return new RegisterRequest(
                "이디야커피 구일역점",
                "02-868-1816",
                "https://www.ediya.com",
                "구일역 근처 좌석 많은 카페. 위치가 위치인 만큼 사람들이 많지 않다. 대신 가는 것도 쉽지 않음,,, 😭",
                "08324",
                "서울특별시 구로구 구일로 110",
                "서울특별시 구로구 구로동 642-108",
                "해원리버파크",
                123.0D,
                241.0D
        );
    }

    @Test
    void register() throws Exception {
        RegisterRequest request = createMockRequestData();
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(registerCommandToJsonString(request))
        );
        resultActions.andExpect(status().isOk());
    }


    @Test
    void search() throws Exception {
        register();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("page", "0")
                        .queryParam("size", "10")
        );

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.page", is(0)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.total", is(1)))
                .andExpect(jsonPath("$.items[0].name", is("이디야커피 구일역점")));
    }
}
