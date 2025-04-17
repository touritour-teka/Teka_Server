package com.teka.application.chatroom.port.out;

import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;

public interface CheckAdminPort {
    boolean checkChatRoomByAdminId(ChatRoomId chatRoomId, AdminId adminId);
}
