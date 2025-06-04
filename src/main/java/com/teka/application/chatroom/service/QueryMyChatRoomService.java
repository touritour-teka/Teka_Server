package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.dto.ChatRoomDto;
import com.teka.application.chatroom.port.in.QueryMyChatRoomUseCase;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryMyChatRoomService implements QueryMyChatRoomUseCase {

    private final FindUserPort findUserPort;
    private final FindChatRoomPort findChatRoomPort;

    public ChatRoomDto execute(UserId userId) {
        User user = findUserPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        ChatRoom chatRoom = findChatRoomPort.findById(user.getChatRoomId())
                .orElseThrow(ChatRoomNotFoundException::new);

        return ChatRoomDto.from(chatRoom);
    }
}
