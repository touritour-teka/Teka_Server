package com.teka.application.auth.exception;

import com.teka.application.auth.exception.error.AuthApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class WrongLoginException extends TekaException {

    public WrongLoginException() {
        super(AuthApplicationErrorProperty.WRONG_LOGIN);
    }
}
