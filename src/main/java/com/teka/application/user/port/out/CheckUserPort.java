package com.teka.application.user.port.out;

import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.PhoneNumber;

public interface CheckUserPort {
    boolean existsByEmail(String email, ChatRoomId chatRoomId);
    boolean existsByPhoneNumber(PhoneNumber phoneNumber, ChatRoomId chatRoomId);
}
