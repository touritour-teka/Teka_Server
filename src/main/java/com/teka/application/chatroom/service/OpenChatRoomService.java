package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.OpenChatRoomUseCase;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.chatroom.port.out.OpenChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class OpenChatRoomService implements OpenChatRoomUseCase {

    private final OpenChatRoomPort openChatRoomPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public void execute(ChatRoomId chatRoomId, AdminId adminId) {
        ChatRoom chatRoom = findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new);
        chatRoom.isAdmin(adminId);
        chatRoom.validateOperatingPeriod(LocalDate.now());

        openChatRoomPort.open(chatRoomId);
    }
}
