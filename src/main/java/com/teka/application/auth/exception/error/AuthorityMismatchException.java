package com.teka.application.auth.exception.error;

import com.teka.shared.error.TekaException;

public class AuthorityMismatchException extends TekaException {
    public AuthorityMismatchException() {
        super(AuthApplicationErrorProperty.AUTHORITY_MISMATCH);
    }
}
