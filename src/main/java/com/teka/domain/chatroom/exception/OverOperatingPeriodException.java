package com.teka.domain.chatroom.exception;

import com.teka.domain.chatroom.exception.error.ChatRoomDomainErrorProperty;
import com.teka.shared.error.TekaException;

public class OverOperatingPeriodException extends TekaException {

    public OverOperatingPeriodException() {
        super(ChatRoomDomainErrorProperty.OVER_OPERATING_PERIOD);
    }
}
