package com.teka.adapter.chatroom.in.web.dto.response;

import com.teka.adapter.user.in.web.dto.response.SimpleUserResponse;
import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.domain.chatroom.type.ChatRoomStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record MyChatRoomResponse(
        Long chatRoomId,
        UUID uuid,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        Long maxParticipants,
        ChatRoomStatus status,
        List<SimpleUserResponse> userList
) {
    public static MyChatRoomResponse from(ChatRoomDto dto) {
        return new MyChatRoomResponse(
                dto.chatRoomId().value(),
                dto.uuid(),
                dto.name(),
                dto.startDate(),
                dto.endDate(),
                dto.maxParticipants(),
                dto.status(),
                dto.userList().stream()
                        .map(SimpleUserResponse::from)
                        .toList()
        );
    }
}
