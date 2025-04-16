package com.teka.domain.user.exception;

import com.teka.domain.user.exception.error.UserDomainErrorProperty;
import com.teka.shared.error.TekaException;

public class PhoneNumberFormatMissMatchException extends TekaException {

    public PhoneNumberFormatMissMatchException() {
        super(UserDomainErrorProperty.PHONE_NUMBER_FORMAT_MISS_MATCH_EXCEPTION);
    }
}
