package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.ChangeUserTypeUseCase;
import com.teka.application.chatroom.port.in.command.ChangeUserTypeCommand;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.port.out.ChangeUserPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChangeUserTypeService implements ChangeUserTypeUseCase {

    private final ChangeUserPort changeUserPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public void execute(List<ChangeUserTypeCommand> commandList, ChatRoomId chatRoomId, AdminId adminId) {
        findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new)
                .isAdmin(adminId);
        commandList.forEach(command -> changeUserPort.changeUserType(command.userId(), command.type()));
    }
}
