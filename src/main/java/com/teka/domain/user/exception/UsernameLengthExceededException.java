package com.teka.domain.user.exception;

import com.teka.domain.user.exception.error.UserDomainErrorProperty;
import com.teka.shared.error.TekaException;

public class UsernameLengthExceededException extends TekaException {
    public UsernameLengthExceededException() {
        super(UserDomainErrorProperty.USERNAME_LENGTH_EXCEEDED);
    }
}
