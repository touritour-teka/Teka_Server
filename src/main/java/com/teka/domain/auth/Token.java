package com.teka.domain.auth;

import com.teka.domain.auth.type.Authority;

public record Token(
        String uuid,
        Authority authority,
        String token
) {
}
