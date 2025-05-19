package com.teka.application.chat.port.dto;

import com.teka.domain.chat.Chat;

import java.time.LocalDateTime;

public record ChatDto(
        Long id,
        String chatRoomUuid,
        String sender,
        String message,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ChatDto from(Chat chat) {
        return new ChatDto(
                chat.getId().value(),
                chat.getChatRoom().getUuid().toString(),
                chat.getUser().getUsername(),
                chat.getMessage(),
                chat.getCreatedAt(),
                chat.getUpdatedAt()
        );
    }
}