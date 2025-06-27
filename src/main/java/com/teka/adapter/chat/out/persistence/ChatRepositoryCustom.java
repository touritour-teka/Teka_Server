package com.teka.adapter.chat.out.persistence;

import com.teka.domain.chatroom.ChatRoom;

import java.util.List;

public interface ChatRepositoryCustom {

    List<ChatJpaEntity> findChats(ChatRoom chatRoom, Long cursor, int size);
}
