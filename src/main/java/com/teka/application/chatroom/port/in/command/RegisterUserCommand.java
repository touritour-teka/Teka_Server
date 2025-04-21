package com.teka.application.chatroom.port.in.command;

import com.teka.domain.user.type.UserType;
import lombok.Builder;

@Builder
public record RegisterUserCommand(
        String phoneNumber,
        String email,
        UserType type
) {
}
