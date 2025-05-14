package com.teka.application.chat.port.in.command;

public record ChatCommand(
        String sender,
        String content
) {
    public ChatCommand(String content) {
        this(null, content);
    }
}
