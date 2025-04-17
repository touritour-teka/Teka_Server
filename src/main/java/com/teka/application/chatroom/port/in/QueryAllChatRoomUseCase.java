package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.dto.ChatRoomSimpleDto;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.type.ChatRoomStatus;

import java.util.List;

public interface QueryAllChatRoomUseCase {
    List<ChatRoomSimpleDto> execute(List<ChatRoomStatus> statusList, AdminId adminId);
}
