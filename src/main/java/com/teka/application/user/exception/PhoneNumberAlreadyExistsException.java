package com.teka.application.user.exception;

import com.teka.application.user.exception.error.UserApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class PhoneNumberAlreadyExistsException extends TekaException {

    public PhoneNumberAlreadyExistsException() {
        super(UserApplicationErrorProperty.PHONE_NUMBER_ALREADY_EXISTS);
    }
}
