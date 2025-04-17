package com.teka.domain.user;

import com.teka.domain.user.exception.EmailFormatMismatchException;

import java.util.regex.Pattern;

public record Email(String value) {
    public Email(String value) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        if (!pattern.matcher(value).matches()) {
            throw new EmailFormatMismatchException();
        }
        this.value = value;
    }
}
