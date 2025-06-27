package com.teka.application.chat.port.in.command;

import com.teka.domain.chat.type.ChatType;

public record ChatCommand(
        ChatType type,
        String message
) {
}
