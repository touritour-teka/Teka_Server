package com.teka.domain.user;

import com.teka.domain.user.exception.PhoneNumberFormatMissMatchException;

public record PhoneNumber(String value) {

    public PhoneNumber(String value) {
        if (value.length() != 11) {
            throw new PhoneNumberFormatMissMatchException();
        }
        this.value = value;
    }
}
