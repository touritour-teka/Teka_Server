package com.teka.application.chat.port.out;

import com.teka.domain.chat.Chat;
import com.teka.domain.chatroom.ChatRoom;

import java.util.List;

public interface FindChatPort {
    List<Chat> findChats(ChatRoom chatRoom, Long cursor, int size);
}
