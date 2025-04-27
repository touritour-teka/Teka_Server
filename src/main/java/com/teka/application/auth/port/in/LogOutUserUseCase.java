package com.teka.application.auth.port.in;

import com.teka.domain.user.UserId;

public interface LogOutUserUseCase {
    void execute(UserId userId);
}
