package com.teka.domain.user.exception;

import com.teka.domain.user.exception.error.UserDomainErrorProperty;
import com.teka.shared.error.TekaException;

public class EmailFormatMissMatchException extends TekaException {

    public EmailFormatMissMatchException() {
        super(UserDomainErrorProperty.EMAIL_FORMAT_MISS_MATCH_EXCEPTION);
    }
}
