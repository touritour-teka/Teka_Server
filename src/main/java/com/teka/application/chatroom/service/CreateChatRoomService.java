package com.teka.application.chatroom.service;

import com.teka.application.admin.exception.AdminNotFoundException;
import com.teka.application.chatroom.port.in.CreateChatRoomUseCase;
import com.teka.application.chatroom.port.in.command.CreateChatRoomCommand;
import com.teka.application.chatroom.port.out.SaveChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoom;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateChatRoomService implements CreateChatRoomUseCase {

    private final SaveChatRoomPort saveChatRoomPort;

    @Override
    public Long execute(CreateChatRoomCommand command, AdminId adminId) {
        ChatRoom chatRoom = ChatRoom.basicBuilder()
                .id(null)
                .name(command.name())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .maxParticipants(command.maxParticipants())
                .adminId(adminId)
                .build();

        try {
            return saveChatRoomPort.save(chatRoom);
        } catch(EntityNotFoundException e) {
            throw new AdminNotFoundException();
        }
    }
}
