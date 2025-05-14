package com.teka.application.chat.port.dto;

public record ChatDto(
        String chatRoomUuid,
        String sender,
        String content
) {
}