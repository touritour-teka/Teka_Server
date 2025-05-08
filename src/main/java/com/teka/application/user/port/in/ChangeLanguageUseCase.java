package com.teka.application.user.port.in;

import com.teka.application.user.port.in.command.ChangeLanguageCommand;
import com.teka.domain.user.UserId;

public interface ChangeLanguageUseCase {
    void execute(UserId userId, ChangeLanguageCommand command);
    
}
