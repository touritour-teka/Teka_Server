package com.teka.adapter.chat.out.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teka.application.chat.port.dto.ChatDto;
import com.teka.shared.constants.WebSocketConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatRedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String payload = new String(message.getBody());
        try {
            ChatDto dto = objectMapper.readValue(payload, ChatDto.class);
            messagingTemplate.convertAndSend(WebSocketConstant.SUBSCRIBE_ENDPOINT + dto.chatRoomUuid(), dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
