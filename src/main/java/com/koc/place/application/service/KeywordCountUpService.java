package com.koc.place.application.service;

import com.koc.place.application.port.in.KeywordCountUpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordCountUpService implements KeywordCountUpUseCase {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void increaseCount(String keyword) {
        rabbitTemplate.convertAndSend("koc", "koc.keyword", keyword);
    }
}
