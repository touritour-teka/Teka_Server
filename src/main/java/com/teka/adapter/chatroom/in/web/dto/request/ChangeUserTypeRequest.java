package com.teka.adapter.chatroom.in.web.dto.request;

import com.teka.application.chatroom.port.in.command.ChangeUserTypeCommand;
import com.teka.domain.user.UserId;
import com.teka.domain.user.type.UserType;
import jakarta.validation.constraints.NotNull;

public record ChangeUserTypeRequest(
        @NotNull(message = "필수값입니다.")
        Long userId,

        @NotNull(message = "필수값입니다.")
        UserType type
) {
    public ChangeUserTypeCommand toCommand() {
        return new ChangeUserTypeCommand(new UserId(userId), type);
    }
}
