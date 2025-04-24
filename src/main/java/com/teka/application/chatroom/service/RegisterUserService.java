package com.teka.application.chatroom.service;

import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.RegisterUserUseCase;
import com.teka.application.chatroom.port.in.command.RegisterUserCommand;
import com.teka.application.user.exception.EmailAlreadyExistsException;
import com.teka.application.user.exception.PhoneNumberAlreadyExistsException;
import com.teka.application.user.port.out.CheckUserPort;
import com.teka.application.user.port.out.SaveUserPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.Email;
import com.teka.domain.user.PhoneNumber;
import com.teka.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;
    private final CheckUserPort checkUserPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public void execute(List<RegisterUserCommand> commandList, ChatRoomId chatRoomId, AdminId adminId) {
        findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new)
                .isAdmin(adminId);
        for (RegisterUserCommand command : commandList) {
            if (checkUserPort.existsByPhoneNumber(new PhoneNumber(command.phoneNumber()), chatRoomId)) {
                throw new PhoneNumberAlreadyExistsException();
            }
            if (checkUserPort.existsByEmail(command.email(), chatRoomId)) {
                throw new EmailAlreadyExistsException();
            }

            User user = User.builder()
                    .id(null)
                    .phoneNumber(new PhoneNumber(command.phoneNumber()))
                    .email(new Email(command.email()))
                    .type(command.type())
                    .chatRoomId(chatRoomId)
                    .build();
            saveUserPort.save(user);
        }
    }
}
