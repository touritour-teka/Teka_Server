package com.teka.domain.user;

import com.teka.domain.user.exception.PhoneNumberFormatMissMatchException;

public record PhoneNumber(String phoneNumber) {

    public PhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) {
            throw new PhoneNumberFormatMissMatchException();
        }
        this.phoneNumber = phoneNumber;
    }
}
