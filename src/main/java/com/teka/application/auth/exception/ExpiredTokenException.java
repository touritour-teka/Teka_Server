package com.teka.application.auth.exception;

import com.teka.application.auth.exception.error.AuthApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class ExpiredTokenException extends TekaException {

    public ExpiredTokenException() {
        super(AuthApplicationErrorProperty.EXPIRED_TOKEN);
    }
}
