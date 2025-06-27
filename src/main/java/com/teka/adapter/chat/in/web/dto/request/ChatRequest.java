package com.teka.adapter.chat.in.web.dto.request;

import com.teka.application.chat.port.in.command.ChatCommand;
import com.teka.domain.chat.type.ChatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatRequest(
        @NotNull(message = "필수값입니다.")
        ChatType type,

        @NotBlank(message = "필수값입니다.")
        String message

) {
    public ChatCommand toCommand() {
        return new ChatCommand(type, message);
    }
}
