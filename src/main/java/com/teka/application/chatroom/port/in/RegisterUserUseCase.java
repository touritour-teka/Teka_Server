package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.in.command.RegisterUserCommand;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;

import java.util.List;

public interface RegisterUserUseCase {
    void execute(List<RegisterUserCommand> commandList, ChatRoomId chatRoomId, AdminId adminId);
}
