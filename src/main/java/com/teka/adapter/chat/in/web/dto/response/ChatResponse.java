package com.teka.adapter.chat.in.web.dto.response;

import com.teka.application.chat.port.dto.ChatDto;
import com.teka.domain.user.type.Language;

import java.time.LocalDateTime;

public record ChatResponse(
        Long id,
        SenderResponse sender,
        String message,
        Language detectedLanguage,
        String translatedMessage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ChatResponse from(ChatDto dto, String translatedMessage) {
        return new ChatResponse(
                dto.id(),
                SenderResponse.from(dto.sender()),
                dto.message(),
                dto.detectedLanguage(),
                translatedMessage,
                dto.createdAt(),
                dto.updatedAt()
        );
    }
}
