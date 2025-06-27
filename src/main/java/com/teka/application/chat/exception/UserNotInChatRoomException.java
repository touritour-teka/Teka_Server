package com.teka.application.chat.exception;

import com.teka.application.chat.exception.error.ChatApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class UserNotInChatRoomException extends TekaException {
    public UserNotInChatRoomException() {
        super(ChatApplicationErrorProperty.USER_NOT_IN_CHAT_ROOM);
    }
}
