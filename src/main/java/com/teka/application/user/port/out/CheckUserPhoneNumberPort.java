package com.teka.application.user.port.out;

import com.teka.domain.chatroom.ChatRoomId;

public interface CheckUserPhoneNumberPort {
    boolean existsByPhoneNumber(String phoneNumber, ChatRoomId chatRoomId);
}
