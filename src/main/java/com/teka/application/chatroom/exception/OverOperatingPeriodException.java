package com.teka.application.chatroom.exception;

import com.teka.application.chatroom.exception.error.ChatRoomApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class OverOperatingPeriodException extends TekaException {

    public OverOperatingPeriodException() {
        super(ChatRoomApplicationErrorProperty.OVER_OPERATING_PERIOD);
    }
}
