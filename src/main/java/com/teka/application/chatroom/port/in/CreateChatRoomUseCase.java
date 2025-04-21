package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.in.command.CreateChatRoomCommand;
import com.teka.domain.admin.AdminId;

public interface CreateChatRoomUseCase {
    Long execute(CreateChatRoomCommand command, AdminId adminId);
}
