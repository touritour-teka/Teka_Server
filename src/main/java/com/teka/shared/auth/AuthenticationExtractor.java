package com.teka.shared.auth;

import com.teka.application.auth.exception.EmptyTokenException;
import com.teka.application.auth.exception.InvalidTokenException;
import com.teka.shared.config.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@RequiredArgsConstructor
@Component
public class AuthenticationExtractor {

    private final JwtProperties jwtProperties;

    public String extract(NativeWebRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader.isBlank()) {
            throw new EmptyTokenException();
        }

        if (!authorizationHeader.startsWith(jwtProperties.getPrefix())) {
            throw new InvalidTokenException();
        }

        return authorizationHeader.replace(jwtProperties.getPrefix(), "").trim();
    }
}
