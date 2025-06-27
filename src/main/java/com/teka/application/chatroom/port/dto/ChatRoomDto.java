package com.teka.application.chatroom.port.dto;

import com.teka.application.user.port.dto.UserDto;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.chatroom.type.ChatRoomStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ChatRoomDto(
        ChatRoomId chatRoomId,
        UUID uuid,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        Long maxParticipants,
        ChatRoomStatus status,
        List<UserDto> userList
) {
    public static ChatRoomDto from(ChatRoom chatRoom) {
        return new ChatRoomDto(
                chatRoom.getId(),
                chatRoom.getUuid(),
                chatRoom.getName(),
                chatRoom.getStartDate(),
                chatRoom.getEndDate(),
                chatRoom.getMaxParticipants(),
                chatRoom.getStatus(),
                chatRoom.getUserList().stream()
                        .map(UserDto::from)
                        .toList()
        );
    }
}
