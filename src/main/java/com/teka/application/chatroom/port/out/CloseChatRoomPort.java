package com.teka.application.chatroom.port.out;

import com.teka.domain.chatroom.ChatRoomId;

public interface CloseChatRoomPort {
    void close(ChatRoomId chatRoomId);
}
