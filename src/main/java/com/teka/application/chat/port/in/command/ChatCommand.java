package com.teka.application.chat.port.in.command;

public record ChatCommand(
        String sender,
        String message
) {
    public ChatCommand(String content) {
        this(null, content);
    }
}
