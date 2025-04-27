package com.teka.application.auth.service;

import com.teka.adapter.auth.out.jwt.JwtTokenProvider;
import com.teka.application.auth.port.dto.TokenDto;
import com.teka.application.auth.port.in.LogInUserUseCase;
import com.teka.application.auth.port.in.command.LogInUserCommand;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.ChangeUserPort;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.auth.type.Authority;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.PhoneNumber;
import com.teka.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LogInUserService implements LogInUserUseCase {

    private final FindUserPort findUserPort;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChangeUserPort changeUserPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public TokenDto execute(String chatRoomUuid, LogInUserCommand command) {
        UUID uuid = UUID.fromString(chatRoomUuid);
        ChatRoom chatRoom = findChatRoomPort.findByUuid(uuid)
                .orElseThrow(ChatRoomNotFoundException::new);
        User user;

        user = findUserPort.findByPhoneNumber(new PhoneNumber(command.phoneNumber()), chatRoom.getId())
                .orElseThrow(UserNotFoundException::new);

        changeUserPort.changeUsername(user.getId(), command.name());
        changeUserPort.changeLanguage(user.getId(), command.language());

        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getId().value().toString(), Authority.USER))
                .refreshToken(jwtTokenProvider.generateRefreshToken(user.getId().value().toString(), Authority.ADMIN))
                .build();
    }
}
