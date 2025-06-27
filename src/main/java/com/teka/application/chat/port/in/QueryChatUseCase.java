package com.teka.application.chat.port.in;

import com.teka.application.chat.port.dto.ChatDto;
import com.teka.domain.user.UserId;

import java.util.List;

public interface QueryChatUseCase {
    List<ChatDto> execute(UserId userId, String chatRoomUuid, Long cursor, int size);
}
