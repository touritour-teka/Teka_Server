package com.teka.adapter.chat.out.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teka.application.chat.port.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatRedisPublisher {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public void publish(String channel, ChatDto dto) {
        try {
            String json = objectMapper.writeValueAsString(dto);
            redisTemplate.convertAndSend(channel, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
