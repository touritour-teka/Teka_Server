package com.teka.application.chat.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChatSubValidationService {

    private final FindUserPort findUserPort;
    private final FindChatRoomPort findChatRoomPort;

    @Transactional(readOnly = true)
    public void execute(UserId userId, String chatRoomUuid) {
        User user = findUserPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        UUID uuid = UUID.fromString(chatRoomUuid);
        ChatRoom chatRoom = findChatRoomPort.findByUuid(uuid)
                .orElseThrow(ChatRoomNotFoundException::new);

        if (!user.getChatRoomId().value().equals(chatRoom.getId().value())) {
            throw new MessagingException("You are not allowed to subscribe to this chat room");
        }
    }
}
