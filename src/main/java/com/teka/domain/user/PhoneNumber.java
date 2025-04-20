package com.teka.domain.user;

import com.teka.domain.user.exception.PhoneNumberFormatMismatchException;

public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value.length() != 11) {
            throw new PhoneNumberFormatMismatchException();
        }
    }
}
