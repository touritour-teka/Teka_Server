package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.in.command.SendMailCommand;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;

import java.util.List;

public interface SendMailUseCase {
    void send(List<SendMailCommand> commandList, ChatRoomId chatRoomId, AdminId adminId);
}
