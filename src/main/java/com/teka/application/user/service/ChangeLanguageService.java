package com.teka.application.user.service;

import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.in.ChangeLanguageUseCase;
import com.teka.application.user.port.in.command.ChangeLanguageCommand;
import com.teka.application.user.port.out.ChangeUserPort;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangeLanguageService implements ChangeLanguageUseCase {

    private final FindUserPort findUserPort;
    private final ChangeUserPort changeUserPort;

    @Override
    public void execute(UserId userId, ChangeLanguageCommand command) {
        findUserPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        changeUserPort.changeLanguage(userId, command.language());
    }
}
