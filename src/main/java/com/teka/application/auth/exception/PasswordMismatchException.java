package com.teka.application.auth.exception;

import com.teka.application.auth.exception.error.AuthApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class PasswordMismatchException extends TekaException {

    public PasswordMismatchException() {
        super(AuthApplicationErrorProperty.PASSWORD_MISMATCH);
    }
}
