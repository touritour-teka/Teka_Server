package com.teka.application.chat.service;

import com.teka.application.chat.port.dto.ChatDto;
import com.teka.application.chat.port.in.QueryChatUseCase;
import com.teka.application.chat.port.out.FindChatPort;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QueryChatService implements QueryChatUseCase {

    private final FindChatPort findChatPort;
    private final FindUserPort findUserPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public List<ChatDto> execute(UserId userId, String chatRoomUuid, Long cursor, int size) {
        User user = findUserPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        ChatRoom chatRoom = findChatRoomPort.findByUuid(UUID.fromString(chatRoomUuid))
                .orElseThrow(ChatRoomNotFoundException::new);

        return findChatPort.findChats(chatRoom, cursor, size)
                .stream()
                .map(ChatDto::from)
                .toList();
    }
}
