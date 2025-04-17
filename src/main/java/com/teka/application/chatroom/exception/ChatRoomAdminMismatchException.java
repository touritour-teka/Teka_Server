package com.teka.application.chatroom.exception;

import com.teka.application.chatroom.exception.error.ChatRoomApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class ChatRoomAdminMismatchException extends TekaException {

    public ChatRoomAdminMismatchException() {
        super(ChatRoomApplicationErrorProperty.CHAT_ROOM_ADMIN_MISMATCH);
    }
}
