package com.teka.application.auth.service;

import com.teka.application.auth.exception.ExpiredTokenException;
import com.teka.application.auth.exception.InvalidTokenException;
import com.teka.application.auth.port.dto.TokenDto;
import com.teka.application.auth.port.in.RefreshTokenUseCase;
import com.teka.application.auth.port.out.FindTokenPort;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.domain.auth.Token;
import com.teka.domain.auth.type.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RefreshTokenService implements RefreshTokenUseCase {

    private final TokenProvider tokenProvider;
    private final FindTokenPort findTokenPort;

    @Override
    @Transactional(readOnly = true)
    public TokenDto execute(String refreshToken) {
        validate(refreshToken);
        Token token = getToken(refreshToken);

        return TokenDto.builder()
                .accessToken(tokenProvider.generateAccessToken(token.uuid()))
                .build();
    }

    private void validate(String token) {
        if (!Objects.equals(tokenProvider.getType(token), TokenType.REFRESH_TOKEN.name())) {
            throw new InvalidTokenException();
        }
    }

    private Token getToken(String refreshToken) {
        String uuid = tokenProvider.getUuid(refreshToken);
        Token token = findTokenPort.findById(uuid)
                .orElseThrow(ExpiredTokenException::new);

        validate(refreshToken, token.token());
        return token;
    }

    private void validate(String expectedToken, String actualToken) {
        if (!Objects.equals(expectedToken, actualToken)) {
            throw new ExpiredTokenException();
        }
    }
}
