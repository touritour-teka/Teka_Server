package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomAdminMismatchException;
import com.teka.application.chatroom.port.in.DeleteChatRoomUseCase;
import com.teka.application.chatroom.port.out.CheckAdminPort;
import com.teka.application.chatroom.port.out.DeleteChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteChatRoomService implements DeleteChatRoomUseCase {

    private final DeleteChatRoomPort deleteChatRoomPort;
    private final CheckAdminPort checkAdminPort;

    @Override
    public void execute(ChatRoomId chatRoomId, AdminId adminId) {
        if (!checkAdminPort.checkChatRoomByAdminId(chatRoomId, adminId)) {
            throw new ChatRoomAdminMismatchException();
        }
        deleteChatRoomPort.deleteById(chatRoomId);
    }
}
