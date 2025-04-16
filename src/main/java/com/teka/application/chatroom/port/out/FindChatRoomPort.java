package com.teka.application.chatroom.port.out;

import com.teka.domain.chatroom.ChatRoom;

import java.util.List;

public interface FindChatRoomPort {
    List<ChatRoom> findAll();
}
