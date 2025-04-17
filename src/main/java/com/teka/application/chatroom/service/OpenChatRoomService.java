package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomAdminMismatchException;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.exception.OverOperatingPeriodException;
import com.teka.application.chatroom.port.in.OpenChatRoomUseCase;
import com.teka.application.chatroom.port.out.CheckAdminPort;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.chatroom.port.out.OpenChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class OpenChatRoomService implements OpenChatRoomUseCase {

    private final OpenChatRoomPort openChatRoomPort;
    private final FindChatRoomPort findChatRoomPort;
    private final CheckAdminPort checkAdminPort;

    @Override
    public void execute(ChatRoomId chatRoomId, AdminId adminId) {
        if (!checkAdminPort.checkChatRoomByAdminId(chatRoomId, adminId)) {
            throw new ChatRoomAdminMismatchException();
        }
        ChatRoom chatRoom = findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new);
        LocalDate now = LocalDate.now();
        if (now.isAfter(chatRoom.getEndDate())) {
            throw new OverOperatingPeriodException();
        }

        openChatRoomPort.open(chatRoomId);
    }
}
