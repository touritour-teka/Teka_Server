package com.teka.application.user.exception;

import com.teka.application.user.exception.error.UserApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class EmailAlreadyExistsException extends TekaException {

    public EmailAlreadyExistsException() {
        super(UserApplicationErrorProperty.EMAIL_ALREADY_EXISTS);
    }
}
