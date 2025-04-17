package com.teka.application.chatroom.port.dto;

import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.chatroom.type.ChatRoomStatus;

import java.time.LocalDate;

public record ChatRoomSimpleDto(
        ChatRoomId chatRoomId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        Long maxParticipants,
        ChatRoomStatus status
) {
    public static ChatRoomSimpleDto from(ChatRoom chatRoom) {
        return new ChatRoomSimpleDto(
                chatRoom.getId(),
                chatRoom.getName(),
                chatRoom.getStartDate(),
                chatRoom.getEndDate(),
                chatRoom.getMaxParticipants(),
                chatRoom.getStatus()
        );
    }
}
