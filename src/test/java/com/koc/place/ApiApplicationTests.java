package com.koc.place;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koc.place.application.port.in.RegisterCommand;
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

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
@Transactional
class ApiApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    public String registerCommandToJsonString(RegisterCommand command) throws JsonProcessingException {
        return mapper.writeValueAsString(command);
    }

    @Test
    void register() throws Exception {
        ResultActions resultActions = registerPlace();
        resultActions.andExpect(status().isOk())
                .andExpect(content().string("1"));
    }


    @Test
    void search() throws Exception {
        registerPlace();

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

    private ResultActions registerPlace() throws Exception {
        RegisterCommand command = new RegisterCommand(
                "이디야커피 구일역점",
                "02-868-1816",
                "https://www.ediya.com",
                List.of("카페"),
                "구일역 근처 좌석 많은 카페. 위치가 위치인 만큼 사람들이 많지 않다. 대신 가는 것도 쉽지 않음,,, 😭",
                "08324",
                "서울특별시 구로구 구일로 110",
                "서울특별시 구로구 구로동 642-108",
                "해원리버파크",
                123.0D,
                241.0D
        );

        return mockMvc.perform(
                MockMvcRequestBuilders.post("/place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(registerCommandToJsonString(command))
        );
    }
}
