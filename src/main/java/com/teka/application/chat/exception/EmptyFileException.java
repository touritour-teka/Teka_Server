package com.teka.application.chat.exception;

import com.teka.application.chat.exception.error.ChatApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class EmptyFileException extends TekaException {
    public EmptyFileException() {
        super(ChatApplicationErrorProperty.EMPTY_FILE);
    }
}
