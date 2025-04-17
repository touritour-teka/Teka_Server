package com.teka.adapter.chatroom.in.web.dto.response;

import com.teka.application.chatroom.port.dto.ChatRoomSimpleDto;
import com.teka.domain.chatroom.type.ChatRoomStatus;

import java.time.LocalDate;

public record ChatRoomSimpleResponse(
        Long chatRoomId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        Long maxParticipants,
        ChatRoomStatus status
) {
    public static ChatRoomSimpleResponse from(ChatRoomSimpleDto chatRoom) {
        return new ChatRoomSimpleResponse(
                chatRoom.chatRoomId().value(),
                chatRoom.name(),
                chatRoom.startDate(),
                chatRoom.endDate(),
                chatRoom.maxParticipants(),
                chatRoom.status()
        );
    }
}
