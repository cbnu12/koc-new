package com.koc.keyword.adapter.in.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koc.keyword.application.port.in.KeywordIncreaseUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KeywordSubscriber {
    private final KeywordIncreaseUseCase keywordIncreaseUseCase;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "koc.keyword")
    public void increase(String text) {
        try {
            KeywordMessage message = objectMapper.readValue(text, KeywordMessage.class);
            keywordIncreaseUseCase.increase(message.getType(), message.getText());
        } catch (JsonProcessingException e) {
            log.error("KeywordSubscriber", e);
        }
    }
}
