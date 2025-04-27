package com.teka.application.auth.port.in;

import com.teka.application.auth.port.dto.TokenDto;
import com.teka.application.auth.port.in.command.LogInUserCommand;

public interface LogInUserUseCase {
    TokenDto execute(String chatRoomUuid, LogInUserCommand command);
}
