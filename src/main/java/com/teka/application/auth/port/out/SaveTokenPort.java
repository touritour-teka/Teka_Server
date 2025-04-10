package com.teka.application.auth.port.out;

import com.teka.domain.auth.Token;

public interface SaveTokenPort {
    void save(Token token);
}
