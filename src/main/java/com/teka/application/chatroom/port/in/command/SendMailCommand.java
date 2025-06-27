package com.teka.application.chatroom.port.in.command;

import com.teka.domain.user.UserId;

public record SendMailCommand(UserId userId) {
}
