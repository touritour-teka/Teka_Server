package com.teka.shared.auth;

import com.teka.application.auth.exception.EmptyTokenException;
import com.teka.application.auth.exception.InvalidTokenException;
import com.teka.shared.config.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketAuthenticationExtractor {

    private final JwtProperties jwtProperties;

    public String extract(StompHeaderAccessor accessor) {
        String authorizationHeader = accessor.getFirstNativeHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            log.error("Empty token");
            throw new MessagingException("Empty token", new EmptyTokenException());
        }

        if (!authorizationHeader.startsWith(jwtProperties.getPrefix())) {
            log.error("Invalid token");
            throw new MessagingException("Invalid token", new InvalidTokenException());
        }

        return authorizationHeader.replace(jwtProperties.getPrefix(), "").trim();
    }
}
