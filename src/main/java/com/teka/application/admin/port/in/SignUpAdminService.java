package com.teka.application.admin.port.in;

import com.teka.application.admin.exception.DuplicateAdminIdException;
import com.teka.application.admin.port.in.command.SignUpAdminCommand;
import com.teka.application.admin.port.out.CheckAdminUsernamePort;
import com.teka.application.admin.port.out.SaveAdminPort;
import com.teka.domain.admin.Admin;
import com.teka.domain.admin.Password;
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
            throw new DuplicateAdminIdException();
        }

        Admin admin = new Admin(null, command.username(), new Password(command.password()));
        saveAdminPort.save(admin);
    }
}
