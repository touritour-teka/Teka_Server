package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.domain.chatroom.exception.ChatRoomAdminMismatchException;
import com.teka.application.chatroom.port.in.DeleteUserUseCase;
import com.teka.application.chatroom.port.in.command.DeleteUserCommand;
import com.teka.application.user.port.out.DeleteUserPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public void execute(List<DeleteUserCommand> commandList, ChatRoomId chatRoomId, AdminId adminId) {
        findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new)
                .isAdmin(adminId);
        commandList.forEach(command -> deleteUserPort.deleteByUserId(command.userId()));
    }
}
