package com.teka.adapter.auth.out.persistence;

import com.teka.application.auth.port.out.FindTokenPort;
import com.teka.application.auth.port.out.SaveTokenPort;
import com.teka.domain.auth.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenPersistenceAdapter implements SaveTokenPort, FindTokenPort {

    private final TokenRepository tokenRepository;

    @Override
    public void save(Token token) {
        TokenRedisEntity entity = TokenRedisEntity.from(token);
        tokenRepository.save(entity);
    }

    @Override
    public Optional<Token> findById(String id) {
        return tokenRepository.findById(id)
                .map(TokenRedisEntity::toDomain);
    }
}
