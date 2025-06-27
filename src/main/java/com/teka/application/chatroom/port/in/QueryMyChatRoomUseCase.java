package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.domain.user.UserId;

public interface QueryMyChatRoomUseCase {
    ChatRoomDto execute(UserId userId);
}
