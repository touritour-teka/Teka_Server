package com.teka.application.chatroom.port.in.command;

import com.teka.domain.user.UserId;
import com.teka.domain.user.type.UserType;

public record ChangeUserTypeCommand(
        UserId userId,
        UserType type
) {
}
