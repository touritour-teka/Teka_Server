package com.teka.adapter.chatroom.in.web.dto.request;

import com.teka.application.chatroom.port.in.command.CreateChatRoomCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateChatRoomRequest(
        @NotBlank(message = "필수값입니다.")
        @Size(max = 30, message = "30자 이하여야합니다.")
        String name,

        @NotNull(message = "필수값입니다.")
        LocalDate startDate,

        @NotNull(message = "필수값입니다.")
        LocalDate endDate,

        @NotNull(message = "필수값입니다.")
        Long maxParticipants
) {
    public CreateChatRoomCommand toCommand() {
        return new CreateChatRoomCommand(name, startDate, endDate, maxParticipants);
    }
}
