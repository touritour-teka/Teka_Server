package com.teka.application.auth.service;

import com.teka.application.auth.exception.InvalidTokenException;
import com.teka.application.auth.port.dto.TokenDto;
import com.teka.application.auth.port.in.RefreshTokenUseCase;
import com.teka.application.auth.port.out.FindTokenPort;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.domain.auth.Token;
import com.teka.domain.auth.type.Authority;
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
        Authority authority = tokenProvider.getAuthority(token.token());

        return TokenDto.builder()
                .accessToken(tokenProvider.generateAccessToken(token.uuid(), authority))
                .build();
    }

    private void validate(String token) {
        if (tokenProvider.getType(token) != TokenType.REFRESH_TOKEN) {
            throw new InvalidTokenException();
        }
    }

    private Token getToken(String refreshToken) {
        String uuid = tokenProvider.getUuid(refreshToken);
        Token token = findTokenPort.findById(uuid)
                .orElseThrow(InvalidTokenException::new);

        validate(refreshToken, token.token());
        return token;
    }

    private void validate(String expectedToken, String actualToken) {
        if (!Objects.equals(expectedToken, actualToken)) {
            throw new InvalidTokenException();
        }
    }
}
