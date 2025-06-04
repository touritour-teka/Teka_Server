package com.teka.adapter.chat.out.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teka.adapter.chat.in.web.dto.response.ChatResponse;
import com.teka.adapter.chat.out.translate.GoogleCloudTranslationAdapter;
import com.teka.application.chat.port.dto.ChatDto;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.chat.type.ChatType;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.type.Language;
import com.teka.shared.constants.WebSocketConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ChatRedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;
    private final FindChatRoomPort findChatRoomPort;
    private final GoogleCloudTranslationAdapter googleCloudTranslationAdapter;
    private final FindUserPort findUserPort;

    @Override
    @Transactional(readOnly = true)
    public void onMessage(Message message, byte[] pattern) {
        String payload = new String(message.getBody());
        try {
            ChatDto dto = objectMapper.readValue(payload, ChatDto.class);
            ChatRoom chatRoom = findChatRoomPort.findByUuid(UUID.fromString(dto.chatRoomUuid()))
                    .orElseThrow(ChatRoomNotFoundException::new);
            Set<Language> languageSet = findUserPort.findLanguagesByChatRoomId(chatRoom.getId());

            for (Language language : languageSet) {
                messagingTemplate.convertAndSend(
                        WebSocketConstant.SUBSCRIBE_ENDPOINT + dto.chatRoomUuid() + "/" + language,
                        dto.type() == ChatType.TEXT && dto.detectedLanguage() != language
                                ? ChatResponse.from(dto, language, googleCloudTranslationAdapter.translateText(language.getCode(), dto.message())) :
                                ChatResponse.from(dto)
                );
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
