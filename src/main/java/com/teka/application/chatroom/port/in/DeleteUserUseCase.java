package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.in.command.DeleteUserCommand;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;

import java.util.List;

public interface DeleteUserUseCase {
    void execute(List<DeleteUserCommand> userIdList, ChatRoomId chatRoomId, AdminId adminId);
}
