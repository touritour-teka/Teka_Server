package com.teka.adapter.chatroom.in.web.dto.request;

import com.teka.application.chatroom.port.in.command.SendMailCommand;
import com.teka.domain.user.UserId;
import jakarta.validation.constraints.NotNull;

public record SendMailRequest(
        @NotNull(message = "필수값입니다.")
        Long userId
) {
    public SendMailCommand toCommand() {
        return new SendMailCommand(new UserId(userId));
    }
}
