package com.teka.adapter.chatroom.in.web.dto.response;

import com.teka.adapter.user.in.web.dto.response.UserResponse;
import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.domain.chatroom.type.ChatRoomStatus;

import java.time.LocalDate;
import java.util.List;

public record ChatRoomResponse(
        Long chatRoomId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        Long maxParticipants,
        ChatRoomStatus status,
        List<UserResponse> userList
) {
    public static ChatRoomResponse from(ChatRoomDto chatRoomDto) {
        return new ChatRoomResponse(
                chatRoomDto.chatRoomId().value(),
                chatRoomDto.name(),
                chatRoomDto.startDate(),
                chatRoomDto.endDate(),
                chatRoomDto.maxParticipants(),
                chatRoomDto.status(),
                chatRoomDto.userList().stream()
                        .map(UserResponse::from)
                        .toList()
        );
    }
}
