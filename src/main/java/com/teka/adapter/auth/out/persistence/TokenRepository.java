package com.teka.adapter.auth.out.persistence;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<TokenRedisEntity, String> {
}
