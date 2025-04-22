package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.SendMailUseCase;
import com.teka.application.chatroom.port.in.command.SendMailCommand;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.chatroom.port.out.SendMailPort;
import com.teka.application.user.port.in.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SendMailService implements SendMailUseCase {

    private final SendMailPort sendMailPort;
    private final FindChatRoomPort findChatRoomPort;
    private final FindUserPort findUserPort;

    @Override
    public void send(List<SendMailCommand> commandList, ChatRoomId chatRoomId, AdminId adminId) {
        ChatRoom chatRoom = findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new);
        chatRoom.isAdmin(adminId);

        for (SendMailCommand command : commandList) {
            User user = findUserPort.findById(command.userId())
                    .orElseThrow(UserNotFoundException::new);
            sendMailPort.send(user.getEmail(), "트까 채팅방 초대", "https://localhost:8080/" + chatRoom.getUuid().toString());
        }
    }
}
