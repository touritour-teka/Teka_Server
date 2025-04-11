package com.teka.application.auth.exception;

import com.teka.application.auth.exception.error.AuthApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class InvalidTokenException extends TekaException {

    public InvalidTokenException() {
        super(AuthApplicationErrorProperty.INVALID_TOKEN);
    }
}
