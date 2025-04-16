package com.teka.adapter.chatroom.in.web.dto.response;

import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.type.ChatRoomStatus;

import java.time.LocalDate;

public record ChatRoomResponse(
        Long chatRoomId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        Long maxParticipants,
        ChatRoomStatus status
) {
    public static ChatRoomResponse from(ChatRoomDto chatRoom) {
        return new ChatRoomResponse(
                chatRoom.chatRoomId().value(),
                chatRoom.name(),
                chatRoom.startDate(),
                chatRoom.endDate(),
                chatRoom.maxParticipants(),
                chatRoom.status()
        );
    }
}
