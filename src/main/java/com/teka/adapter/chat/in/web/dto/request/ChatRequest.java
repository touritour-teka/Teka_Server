package com.teka.adapter.chat.in.web.dto.request;

import com.teka.application.chat.port.in.command.ChatCommand;
import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank(message = "필수값입니다.")
        String message
) {
    public ChatCommand toCommand() {
        return new ChatCommand(message);
    }
}
