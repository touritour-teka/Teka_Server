package com.teka.application.chat.port.in;

import com.teka.application.chat.port.in.command.ChatCommand;
import com.teka.domain.user.UserId;

public interface SendChatUseCase {
    void execute(UserId userId, String chatRoomUuid, ChatCommand command);
}
