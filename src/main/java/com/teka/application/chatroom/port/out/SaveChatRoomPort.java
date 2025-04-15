package com.teka.application.chatroom.port.out;

import com.teka.domain.chatroom.ChatRoom;

public interface SaveChatRoomPort {
    Long save(ChatRoom chatRoom);
}
