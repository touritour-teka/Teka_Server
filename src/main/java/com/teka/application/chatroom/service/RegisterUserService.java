package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomAdminMismatchException;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.RegisterUserUseCase;
import com.teka.application.chatroom.port.in.command.RegisterUserCommand;
import com.teka.application.chatroom.port.out.CheckAdminPort;
import com.teka.application.user.port.in.exception.EmailAlreadyExistsException;
import com.teka.application.user.port.in.exception.PhoneNumberAlreadyExistsException;
import com.teka.application.user.port.out.CheckUserEmailPort;
import com.teka.application.user.port.out.CheckUserPhoneNumberPort;
import com.teka.application.user.port.out.SaveUserPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.Email;
import com.teka.domain.user.PhoneNumber;
import com.teka.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final SaveUserPort saveUserPort;
    private final CheckUserPhoneNumberPort checkUserPhoneNumberPort;
    private final CheckUserEmailPort checkUserEmailPort;
    private final CheckAdminPort checkAdminPort;

    @Override
    public void execute(List<RegisterUserCommand> commandList, ChatRoomId chatRoomId, AdminId adminId) {
        if(!checkAdminPort.checkChatRoomByAdminId(chatRoomId, adminId)) {
            throw new ChatRoomAdminMismatchException();
        }
        for (RegisterUserCommand command : commandList) {
            try {
                if (checkUserPhoneNumberPort.existsByPhoneNumber(command.phoneNumber(), chatRoomId)) {
                    throw new PhoneNumberAlreadyExistsException();
                }
                if (checkUserEmailPort.existsByEmail(command.email(), chatRoomId)) {
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
            } catch (EntityNotFoundException e) {
                throw new ChatRoomNotFoundException();

            }
        }
    }
}
