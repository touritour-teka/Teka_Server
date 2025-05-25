package com.teka.application.chat.port.dto;

import com.teka.domain.chat.Chat;
import com.teka.domain.user.type.Language;

import java.time.LocalDateTime;

public record ChatDto(
        Long id,
        String chatRoomUuid,
        SenderDto sender,
        Language detectedLanguage,
        String message,
        Language targetLanguage,
        String translatedMessage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ChatDto from(Chat chat) {
        return new ChatDto(
                chat.getId().value(),
                chat.getChatRoom().getUuid().toString(),
                new SenderDto(chat.getUser().getId(), chat.getUser().getUsername()),
                chat.getDetectedLanguage(),
                chat.getMessage(),
                null,
                null,
                chat.getCreatedAt(),
                chat.getUpdatedAt()
        );
    }

    public static ChatDto from(Chat chat, Language targetLanguage, String translatedMessage) {
        return new ChatDto(
                chat.getId().value(),
                chat.getChatRoom().getUuid().toString(),
                new SenderDto(chat.getUser().getId(), chat.getUser().getUsername()),
                chat.getDetectedLanguage(),
                chat.getMessage(),
                targetLanguage,
                translatedMessage,
                chat.getCreatedAt(),
                chat.getUpdatedAt()
        );
    }
}