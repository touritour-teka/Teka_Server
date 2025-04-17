package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.in.command.ChangeUserTypeCommand;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;

import java.util.List;

public interface ChangeUserTypeUseCase {
    void execute(List<ChangeUserTypeCommand> commandList, ChatRoomId chatRoomId, AdminId adminId);
}
