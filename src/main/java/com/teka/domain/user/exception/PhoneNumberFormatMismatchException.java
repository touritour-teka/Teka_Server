package com.teka.domain.user.exception;

import com.teka.domain.user.exception.error.UserDomainErrorProperty;
import com.teka.shared.error.TekaException;

public class PhoneNumberFormatMismatchException extends TekaException {

    public PhoneNumberFormatMismatchException() {
        super(UserDomainErrorProperty.PHONE_NUMBER_FORMAT_MISMATCH_EXCEPTION);
    }
}
