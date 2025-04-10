package com.teka.application.admin.port.in;

import com.teka.application.admin.port.in.command.SignUpAdminCommand;

public interface SignUpAdminUseCase {
    void execute(SignUpAdminCommand command);
}
