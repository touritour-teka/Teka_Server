package com.teka.application.auth.service;

import com.teka.application.admin.exception.AdminNotFoundException;
import com.teka.application.admin.port.out.FindAdminPort;
import com.teka.application.auth.exception.PasswordMismatchException;
import com.teka.application.auth.exception.WrongLoginException;
import com.teka.application.auth.port.dto.TokenDto;
import com.teka.application.auth.port.in.LogInAdminUseCase;
import com.teka.application.auth.port.in.command.LogInAdminCommand;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.domain.admin.Admin;
import com.teka.domain.admin.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginAdminService implements LogInAdminUseCase {

    private final FindAdminPort findAdminPort;
    private final TokenProvider tokenProvider;

    @Override
    public TokenDto execute(LogInAdminCommand command) {
        Admin admin;
        try {
            admin = findAdminPort.findByUsername(command.username())
                    .orElseThrow(AdminNotFoundException::new);
            validatePassword(command.password(), admin.getPassword());
        } catch (AdminNotFoundException | PasswordMismatchException e) {
            throw new WrongLoginException();
        }

        return TokenDto.builder()
                .accessToken(tokenProvider.generateAccessToken(admin.getUsername()))
                .refreshToken(tokenProvider.generateRefreshToken(admin.getUsername()))
                .build();
    }

    private void validatePassword(String rawPassword, Password encodedPassword) {
        if (!encodedPassword.matches(rawPassword)) {
            throw new PasswordMismatchException();
        }
    }
}
