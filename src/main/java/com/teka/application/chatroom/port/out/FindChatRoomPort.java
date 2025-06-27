package com.teka.application.chatroom.port.out;

import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindChatRoomPort {
    List<ChatRoom> findByAdminId(AdminId adminId);
    Optional<ChatRoom> findById(ChatRoomId chatRoomId);
    Optional<ChatRoom> findByUuid(UUID uuid);
}
