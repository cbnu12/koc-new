package com.koc.place.adapter.out.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koc.keyword.domain.KeywordType;
import com.koc.place.application.port.out.KeywordCountUpPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class KeywordCountUpAdapter implements KeywordCountUpPort {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void increaseCount(Long placeId) {
        try {
            KeywordMessage message = new KeywordMessage(KeywordType.PLACE, placeId.toString());
            String json = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend("koc", "koc.keyword", json);
        } catch (JsonProcessingException e) {
            log.error("KeywordCountUpAdapter", e);
        }
    }
}
