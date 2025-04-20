package com.teka.adapter.chatroom.in.web.dto.request;

import com.teka.application.chatroom.port.in.command.DeleteUserCommand;
import com.teka.domain.user.UserId;

public record DeleteUserRequest(Long userId) {
    public DeleteUserCommand toCommand() {
        return new DeleteUserCommand(new UserId(userId));
    }
}
