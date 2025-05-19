package com.teka.adapter.chat.in.web.dto.response;

import com.teka.application.chat.port.dto.ChatDto;

import java.time.LocalDateTime;

public record ChatResponse(
        Long id,
        String sender,
        String message,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ChatResponse from(ChatDto dto) {
        return new ChatResponse(
                dto.id(),
                dto.sender(),
                dto.message(),
                dto.createdAt(),
                dto.updatedAt()
        );
    }
}
