package com.teka.application.chatroom.port.in;

import com.teka.application.chatroom.port.in.command.ChangeUserTypeCommand;

import java.util.List;

public interface ChangeUserTypeUseCase {
    void execute(List<ChangeUserTypeCommand> commandList);
}
