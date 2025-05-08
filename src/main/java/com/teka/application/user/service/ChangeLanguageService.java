package com.teka.application.user.service;

import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.in.ChangeLanguageUseCase;
import com.teka.application.user.port.in.command.ChangeLanguageCommand;
import com.teka.application.user.port.out.ChangeUserPort;
import com.teka.domain.user.UserId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangeLanguageService implements ChangeLanguageUseCase {

    private final ChangeUserPort changeUserPort;

    @Override
    public void execute(UserId userId, ChangeLanguageCommand command) {
        try {
            changeUserPort.changeLanguage(userId, command.language());
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException();
        }
    }
}
