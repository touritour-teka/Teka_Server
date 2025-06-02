package com.teka.adapter.chat.in.web.dto.response;

import com.teka.application.chat.port.dto.ChatDto;
import com.teka.domain.chat.type.ChatType;
import com.teka.domain.user.type.Language;

import java.time.LocalDateTime;

public record ChatResponse(
        Long id,
        SenderResponse sender,
        ChatType type,
        Language detectedLanguage,
        String message,
        Language targetLanguage,
        String translatedMessage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ChatResponse from(ChatDto dto) {
        return new ChatResponse(
                dto.id(),
                SenderResponse.from(dto.sender()),
                dto.type(),
                dto.detectedLanguage(),
                dto.message(),
                dto.targetLanguage(),
                dto.translatedMessage(),
                dto.createdAt(),
                dto.updatedAt()
        );
    }

    public static ChatResponse from(ChatDto dto, Language targetLanguage, String translatedMessage) {
        return new ChatResponse(
                dto.id(),
                SenderResponse.from(dto.sender()),
                dto.type(),
                dto.detectedLanguage(),
                dto.message(),
                targetLanguage,
                translatedMessage,
                dto.createdAt(),
                dto.updatedAt()
        );
    }
}
