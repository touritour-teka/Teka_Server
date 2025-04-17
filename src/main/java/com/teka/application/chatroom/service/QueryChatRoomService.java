package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomAdminMismatchException;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.application.chatroom.port.in.QueryChatRoomUseCase;
import com.teka.application.chatroom.port.out.CheckAdminPort;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryChatRoomService implements QueryChatRoomUseCase {

    private final FindChatRoomPort findChatRoomPort;
    private final CheckAdminPort checkAdminPort;

    @Override
    public ChatRoomDto execute(ChatRoomId chatRoomId, AdminId adminId) {
        if (!checkAdminPort.checkChatRoomByAdminId(chatRoomId, adminId)) {
            throw new ChatRoomAdminMismatchException();
        }
        return findChatRoomPort.findById(chatRoomId)
                .map(ChatRoomDto::from)
                .orElseThrow(ChatRoomNotFoundException::new);
    }
}
