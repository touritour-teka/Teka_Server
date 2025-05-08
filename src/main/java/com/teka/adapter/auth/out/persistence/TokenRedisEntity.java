package com.teka.adapter.auth.out.persistence;

import com.teka.domain.auth.Token;
import com.teka.domain.auth.type.Authority;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 15)
public class TokenRedisEntity {

    @Id
    private String uuid;

    private Authority authority;

    private String token;

    public Token toDomain() {
        return new Token(uuid, authority, token);
    }

    public static TokenRedisEntity from(Token token) {
        return new TokenRedisEntity(token.uuid(), token.authority(), token.token());
    }
}
