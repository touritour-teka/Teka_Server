package com.teka.application.auth.port.out;

import com.teka.domain.auth.type.Authority;
import com.teka.domain.auth.type.TokenType;

public interface TokenProvider {
    String generateAccessToken(String uuid, Authority authority);
    String generateRefreshToken(String uuid, Authority authority);
    String getUuid(String token);
    TokenType getType(String token);
    Authority getAuthority(String token);
}
