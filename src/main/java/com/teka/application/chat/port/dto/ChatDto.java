package com.teka.application.chat.port.dto;

import com.teka.domain.chat.Chat;
import com.teka.domain.user.type.Language;

import java.time.LocalDateTime;

public record ChatDto(
        Long id,
        String chatRoomUuid,
        SenderDto sender,
        String message,
        Language detectedLanguage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ChatDto from(Chat chat) {
        return new ChatDto(
                chat.getId().value(),
                chat.getChatRoom().getUuid().toString(),
                new SenderDto(chat.getUser().getId(), chat.getUser().getUsername()),
                chat.getMessage(),
                chat.getDetectedLanguage(),
                chat.getCreatedAt(),
                chat.getUpdatedAt()
        );
    }
}