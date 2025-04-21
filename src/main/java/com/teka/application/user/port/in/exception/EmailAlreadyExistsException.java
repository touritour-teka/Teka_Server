package com.teka.application.user.port.in.exception;

import com.teka.application.user.port.in.exception.error.UserApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class EmailAlreadyExistsException extends TekaException {

    public EmailAlreadyExistsException() {
        super(UserApplicationErrorProperty.EMAIL_ALREADY_EXISTS);
    }
}
