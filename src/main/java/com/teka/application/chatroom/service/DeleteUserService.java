package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomAdminMismatchException;
import com.teka.application.chatroom.port.in.DeleteUserUseCase;
import com.teka.application.chatroom.port.out.CheckAdminPort;
import com.teka.application.user.port.out.DeleteUserPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;
    private final CheckAdminPort checkAdminPort;

    @Override
    public void execute(List<UserId> userIdList, ChatRoomId chatRoomId, AdminId adminId) {
        if (!checkAdminPort.checkChatRoomByAdminId(chatRoomId, adminId)) {
            throw new ChatRoomAdminMismatchException();
        }
        userIdList.forEach(deleteUserPort::deleteByUserId);
    }
}
