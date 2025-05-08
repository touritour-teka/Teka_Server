package com.teka.application.auth.service;

import com.teka.application.auth.port.in.LogOutUserUseCase;
import com.teka.application.auth.port.out.DeleteTokenPort;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogOutUserService implements LogOutUserUseCase {

    private final FindUserPort findUserPort;
    private final DeleteTokenPort deleteTokenPort;

    @Override
    public void execute(UserId userId) {
        findUserPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        deleteTokenPort.deleteById(userId.value().toString());
    }
}
