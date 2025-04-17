package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.CloseChatRoomUseCase;
import com.teka.application.chatroom.port.out.CloseChatRoomPort;
import com.teka.domain.chatroom.ChatRoomId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CloseChatRoomService implements CloseChatRoomUseCase {

    private final CloseChatRoomPort closeChatRoomPort;

    @Override
    public void execute(ChatRoomId chatRoomId) {
        try {
            closeChatRoomPort.close(chatRoomId);
        } catch(EntityNotFoundException e) {
            throw new ChatRoomNotFoundException();
        }
    }
}
