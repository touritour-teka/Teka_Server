package com.teka.application.chatroom.service;

import com.teka.application.chatroom.port.dto.ChatRoomSimpleDto;
import com.teka.application.chatroom.port.in.QueryAllChatRoomUseCase;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.type.ChatRoomStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryAllChatRoomService implements QueryAllChatRoomUseCase {

    private final FindChatRoomPort findChatRoomPort;

    @Override
    public List<ChatRoomSimpleDto> execute(List<ChatRoomStatus> statusList, AdminId adminId) {
        return findChatRoomPort.findByAdminId(adminId).stream()
                .filter(chatRoom -> statusList.contains(chatRoom.getStatus()))
                .map(ChatRoomSimpleDto::from)
                .toList();
    }
}
