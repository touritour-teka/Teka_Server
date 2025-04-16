package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.domain.chatroom.type.ChatRoomStatus;

import java.util.List;

public interface QueryAllChatRoomUseCase {
    List<ChatRoomDto> execute(List<ChatRoomStatus> statusList);
}
