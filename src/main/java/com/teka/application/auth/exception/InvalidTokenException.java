package com.teka.application.auth.exception;

import com.teka.application.auth.exception.error.TokenApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class InvalidTokenException extends TekaException {

    public InvalidTokenException() {
        super(TokenApplicationErrorProperty.INVALID_TOKEN);
    }
}
