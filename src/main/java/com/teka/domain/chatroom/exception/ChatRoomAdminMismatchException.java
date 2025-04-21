package com.teka.domain.chatroom.exception;

import com.teka.domain.chatroom.exception.error.ChatRoomDomainErrorProperty;
import com.teka.shared.error.TekaException;

public class ChatRoomAdminMismatchException extends TekaException {

    public ChatRoomAdminMismatchException() {
        super(ChatRoomDomainErrorProperty.CHAT_ROOM_ADMIN_MISMATCH);
    }
}
