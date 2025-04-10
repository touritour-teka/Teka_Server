package com.teka.application.auth.port.out;

public interface TokenProvider {
    String generateAccessToken(String uuid);
    String generateRefreshToken(String uuid);
    String getUuid(String uuid);
    String getType(String token);
}
