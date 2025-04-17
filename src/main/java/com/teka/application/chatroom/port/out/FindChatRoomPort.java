package com.teka.application.chatroom.port.out;

import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;

import java.util.List;
import java.util.Optional;

public interface FindChatRoomPort {
    List<ChatRoom> findAll();
    Optional<ChatRoom> findById(ChatRoomId chatRoomId);
}
