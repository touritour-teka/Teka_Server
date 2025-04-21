package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.application.chatroom.port.in.QueryChatRoomUseCase;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryChatRoomService implements QueryChatRoomUseCase {

    private final FindChatRoomPort findChatRoomPort;

    @Override
    public ChatRoomDto execute(ChatRoomId chatRoomId, AdminId adminId) {
        ChatRoom chatRoom =  findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new);
        chatRoom.isAdmin(adminId);

        return ChatRoomDto.from(chatRoom);
    }
}
