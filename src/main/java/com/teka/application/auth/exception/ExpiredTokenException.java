package com.teka.application.auth.exception;

import com.teka.application.auth.exception.error.TokenApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class ExpiredTokenException extends TekaException {

    public ExpiredTokenException() {
        super(TokenApplicationErrorProperty.EXPIRED_TOKEN);
    }
}
