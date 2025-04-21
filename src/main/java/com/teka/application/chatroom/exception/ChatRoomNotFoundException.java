package com.teka.application.chatroom.exception;

import com.teka.application.chatroom.exception.error.ChatRoomApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class ChatRoomNotFoundException extends TekaException {

    public ChatRoomNotFoundException() {
        super(ChatRoomApplicationErrorProperty.CHAT_ROOM_NOT_FOUND);
    }
}
