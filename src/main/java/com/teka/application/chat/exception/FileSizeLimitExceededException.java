package com.teka.application.chat.exception;

import com.teka.application.chat.exception.error.ChatApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class FileSizeLimitExceededException extends TekaException {
    public FileSizeLimitExceededException() {
        super(ChatApplicationErrorProperty.FILE_SIZE_LIMIT_EXCEEDED);
    }
}
