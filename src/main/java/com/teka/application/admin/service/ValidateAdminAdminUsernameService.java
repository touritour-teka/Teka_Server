package com.teka.application.admin.service;

import com.teka.application.admin.exception.AdminIdAlreadyExistsException;
import com.teka.application.admin.port.in.ValidateAdminUsernameUseCase;
import com.teka.application.admin.port.out.CheckAdminUsernamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ValidateAdminAdminUsernameService implements ValidateAdminUsernameUseCase {

    private final CheckAdminUsernamePort checkAdminUsernamePort;

    @Override
    public void execute(String username) {
        if (checkAdminUsernamePort.existsByUsername(username)) {
            throw new AdminIdAlreadyExistsException();
        }
    }
}
