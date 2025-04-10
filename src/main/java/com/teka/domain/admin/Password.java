package com.teka.domain.admin;

import com.teka.shared.util.PasswordUtil;

public record Password(String value) {

    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수값입니다.");
        }
        value = PasswordUtil.encode(value);
    }

    public boolean matches(String password) {
        return PasswordUtil.matches(value, password);
    }
}
