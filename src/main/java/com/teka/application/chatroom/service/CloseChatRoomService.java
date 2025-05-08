package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.CloseChatRoomUseCase;
import com.teka.application.chatroom.port.out.CloseChatRoomPort;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CloseChatRoomService implements CloseChatRoomUseCase {

    private final CloseChatRoomPort closeChatRoomPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public void execute(ChatRoomId chatRoomId, AdminId adminId) {
        findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new)
                .isAdmin(adminId);
        closeChatRoomPort.close(chatRoomId);
    }
}
