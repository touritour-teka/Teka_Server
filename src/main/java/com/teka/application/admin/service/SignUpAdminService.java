package com.teka.application.admin.service;

import com.teka.application.admin.exception.AdminIdAlreadyExistsException;
import com.teka.application.admin.port.in.SignUpAdminUseCase;
import com.teka.application.admin.port.in.command.SignUpAdminCommand;
import com.teka.application.admin.port.out.CheckAdminUsernamePort;
import com.teka.application.admin.port.out.SaveAdminPort;
import com.teka.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpAdminService implements SignUpAdminUseCase {

    private final SaveAdminPort saveAdminPort;
    private final CheckAdminUsernamePort checkAdminUsernamePort;

    @Override
    public void execute(SignUpAdminCommand command) {
        if (checkAdminUsernamePort.existsByUsername(command.username())) {
            throw new AdminIdAlreadyExistsException();
        }

        Admin admin = new Admin(null, command.username(), command.password());
        saveAdminPort.save(admin);
    }
}
