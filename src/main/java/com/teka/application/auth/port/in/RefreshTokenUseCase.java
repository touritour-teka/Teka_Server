package com.teka.application.auth.port.in;

import com.teka.application.auth.port.dto.TokenDto;

public interface RefreshTokenUseCase {
    TokenDto execute(String refreshToken);
}
