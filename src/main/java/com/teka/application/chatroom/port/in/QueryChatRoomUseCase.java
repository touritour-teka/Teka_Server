package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;

public interface QueryChatRoomUseCase {
    ChatRoomDto execute(ChatRoomId chatRoomId, AdminId adminId);
}
