package com.teka.application.auth.port.out;

import com.teka.domain.auth.Token;

import java.util.Optional;

public interface FindTokenPort {
    Optional<Token> findById(String id);
}
