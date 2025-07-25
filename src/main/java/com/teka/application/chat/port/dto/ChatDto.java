package com.teka.application.chat.port.dto;

import com.teka.application.user.port.dto.SimpleUserDto;
import com.teka.domain.chat.Chat;
import com.teka.domain.chat.type.ChatType;
import com.teka.domain.user.type.Language;

import java.time.LocalDateTime;

public record ChatDto(
        Long id,
        String chatRoomUuid,
        SimpleUserDto user,
        ChatType type,
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
                new SimpleUserDto(
                        chat.getUser().getId(),
                        chat.getUser().getUsername(),
                        chat.getUser().getLanguage(),
                        chat.getUser().getType()
                ),
                chat.getType(),
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
                new SimpleUserDto(
                        chat.getUser().getId(),
                        chat.getUser().getUsername(),
                        chat.getUser().getLanguage(),
                        chat.getUser().getType()
                ),
                chat.getType(),
                chat.getDetectedLanguage(),
                chat.getMessage(),
                targetLanguage,
                translatedMessage,
                chat.getCreatedAt(),
                chat.getUpdatedAt()
        );
    }
}