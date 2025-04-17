package com.teka.application.chatroom.port.out;

import com.teka.domain.chatroom.ChatRoomId;

public interface OpenChatRoomPort {
    void open(ChatRoomId chatRoomId);
}
