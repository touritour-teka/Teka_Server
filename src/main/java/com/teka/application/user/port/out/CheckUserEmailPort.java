package com.teka.application.user.port.out;

import com.teka.domain.chatroom.ChatRoomId;

public interface CheckUserEmailPort {
    boolean existsByEmail(String email, ChatRoomId chatRoomId);
}
