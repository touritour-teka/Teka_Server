package com.teka.application.chat.exception;

import com.teka.application.chat.exception.error.ChatApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class MediaTypeMismatchException extends TekaException {
    public MediaTypeMismatchException() {
        super(ChatApplicationErrorProperty.MEDIA_TYPE_MISMATCH);
    }
}
