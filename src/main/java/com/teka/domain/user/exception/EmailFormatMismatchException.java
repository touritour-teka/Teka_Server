package com.teka.domain.user.exception;

import com.teka.domain.user.exception.error.UserDomainErrorProperty;
import com.teka.shared.error.TekaException;

public class EmailFormatMismatchException extends TekaException {

    public EmailFormatMismatchException() {
        super(UserDomainErrorProperty.EMAIL_FORMAT_MISMATCH_EXCEPTION);
    }
}
