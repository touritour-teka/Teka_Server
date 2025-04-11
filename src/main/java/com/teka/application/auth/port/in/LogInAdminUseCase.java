package com.teka.application.auth.port.in;

import com.teka.application.auth.port.in.command.LogInAdminCommand;
import com.teka.application.auth.port.dto.TokenDto;

public interface LogInAdminUseCase {
    TokenDto execute(LogInAdminCommand command);
}
