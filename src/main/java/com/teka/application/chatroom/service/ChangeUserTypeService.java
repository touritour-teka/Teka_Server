package com.teka.application.chatroom.service;

import com.teka.application.chatroom.port.in.ChangeUserTypeUseCase;
import com.teka.application.chatroom.port.in.command.ChangeUserTypeCommand;
import com.teka.application.user.port.out.ChangeUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChangeUserTypeService implements ChangeUserTypeUseCase {

    private final ChangeUserPort changeUserPort;

    @Override
    public void execute(List<ChangeUserTypeCommand> commandList) {
        commandList.forEach(command -> changeUserPort.changeUserType(command.userId(), command.type()));
    }
}
