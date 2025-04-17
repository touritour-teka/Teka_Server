package com.teka.application.chatroom.port.in;

import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;

public interface DeleteChatRoomUseCase {
    void execute(ChatRoomId chatRoomId, AdminId adminId);
}
