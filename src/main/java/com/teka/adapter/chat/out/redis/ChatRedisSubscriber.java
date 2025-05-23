package com.teka.adapter.chat.out.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teka.adapter.chat.in.web.dto.response.ChatResponse;
import com.teka.adapter.chat.out.translate.GoogleCloudTranslationAdapter;
import com.teka.application.chat.port.dto.ChatDto;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.User;
import com.teka.domain.user.type.Language;
import com.teka.shared.constants.WebSocketConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ChatRedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;
    private final FindChatRoomPort findChatRoomPort;
    private final GoogleCloudTranslationAdapter googleCloudTranslationAdapter;

    @Override
    @Transactional(readOnly = true)
    public void onMessage(Message message, byte[] pattern) {
        String payload = new String(message.getBody());
        try {
            ChatDto dto = objectMapper.readValue(payload, ChatDto.class);
            ChatRoom chatRoom = findChatRoomPort.findByUuid(UUID.fromString(dto.chatRoomUuid()))
                    .orElseThrow(ChatRoomNotFoundException::new);
            List<User> userList = chatRoom.getUserList();
            Map<Language, List<User>> userByLanguage = userList.stream()
                    .collect(Collectors.groupingBy(User::getLanguage));

            for (Map.Entry<Language, List<User>> entry : userByLanguage.entrySet()) {
                Language language = entry.getKey();
                List<User> users = entry.getValue();
                String translatedMessage = googleCloudTranslationAdapter.translateText(language.getCode(), dto.message());

                for (User user : users) {
                    messagingTemplate.convertAndSendToUser(
                            user.getId().value().toString(),
                            WebSocketConstant.SUBSCRIBE_ENDPOINT + dto.chatRoomUuid(),
                            ChatResponse.from(dto, language != dto.detectedLanguage() ? translatedMessage : null)
                    );
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
