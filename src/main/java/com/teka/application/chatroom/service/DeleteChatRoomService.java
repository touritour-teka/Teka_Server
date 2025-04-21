package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.DeleteChatRoomUseCase;
import com.teka.application.chatroom.port.out.DeleteChatRoomPort;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteChatRoomService implements DeleteChatRoomUseCase {

    private final DeleteChatRoomPort deleteChatRoomPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public void execute(ChatRoomId chatRoomId, AdminId adminId) {
        findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new)
                .isAdmin(adminId);
        deleteChatRoomPort.deleteById(chatRoomId);
    }
}
