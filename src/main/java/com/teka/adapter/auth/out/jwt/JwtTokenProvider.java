package com.teka.adapter.auth.out.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teka.application.auth.exception.ExpiredTokenException;
import com.teka.application.auth.exception.InvalidTokenException;
import com.teka.application.auth.port.out.SaveTokenPort;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.domain.auth.Token;
import com.teka.domain.auth.type.TokenType;
import com.teka.shared.config.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtTokenProvider implements TokenProvider {

    private final JwtProperties jwtProperties;
    private final SaveTokenPort saveTokenPort;

    @Override
    public String generateAccessToken(String uuid) {
        return generateToken(uuid, TokenType.ACCESS_TOKEN, jwtProperties.getAccessExpirationTime());
    }

    @Override
    public String generateRefreshToken(String uuid) {
        String token = generateToken(uuid, TokenType.REFRESH_TOKEN, jwtProperties.getRefreshExpirationTime());
        saveTokenPort.save(new Token(uuid, token));
        return token;
    }

    @Override
    public String getUuid(String uuid) {
        return extractAllClaims(uuid).get("uuid", String.class);
    }

    @Override
    public String getType(String token) {
        return extractAllClaims(token).get("type", String.class);
    }

    private String generateToken(String uuid, TokenType type, Long time) {
        Date now = new Date();

        return Jwts.builder()
                .claim("uuid", uuid)
                .claim("type", type.name())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + time))
                .signWith(getSigningKey(jwtProperties.getSecretKey()), Jwts.SIG.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        try {
            validateSignatureAlgorithm(token);

            return Jwts.parser()
                    .verifyWith(getSigningKey(jwtProperties.getSecretKey()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    private void validateSignatureAlgorithm(String token) throws JsonProcessingException {
        String[] tokenParts = token.split("\\.");
        String headerJson = new String(Base64.getDecoder().decode(tokenParts[0]));

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> headerMap = objectMapper.readValue(headerJson, new TypeReference<>() {
        });
        String algorithm = (String) headerMap.get("alg");

        if (!algorithm.equals(Jwts.SIG.HS256.toString())) {
            throw new InvalidTokenException();
        }
    }

    private SecretKey getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
