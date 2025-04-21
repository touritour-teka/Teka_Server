package com.teka.application.chatroom.port.in.command;

import java.time.LocalDate;

public record CreateChatRoomCommand(
        String name,
        LocalDate startDate,
        LocalDate endDate,
        Long maxParticipants
) {
}
