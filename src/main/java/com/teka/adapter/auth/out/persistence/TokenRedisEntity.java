package com.teka.adapter.auth.out.persistence;

import com.teka.domain.auth.Token;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 15)
public class TokenRedisEntity {

    @Id
    private String uuid;

    private String token;

    public Token toDomain() {
        return new Token(uuid, token);
    }

    public static TokenRedisEntity from(Token token) {
        return new TokenRedisEntity(token.uuid(), token.token());
    }
}
