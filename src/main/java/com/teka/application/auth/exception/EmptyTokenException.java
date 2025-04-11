package com.teka.application.auth.exception;

import com.teka.application.auth.exception.error.AuthApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class EmptyTokenException extends TekaException {

    public EmptyTokenException() {
        super(AuthApplicationErrorProperty.EMPTY_TOKEN);
    }
}
