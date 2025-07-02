package com.teka.application.chat.service;

import com.teka.adapter.chat.out.redis.ChatRedisPublisher;
import com.teka.adapter.chat.out.translate.GoogleCloudTranslationAdapter;
import com.teka.application.auth.exception.error.AuthorityMismatchException;
import com.teka.application.chat.exception.UserNotInChatRoomException;
import com.teka.application.chat.port.dto.ChatDto;
import com.teka.application.chat.port.in.SendChatUseCase;
import com.teka.application.chat.port.in.command.ChatCommand;
import com.teka.application.chat.port.out.SaveChatPort;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.chat.Chat;
import com.teka.domain.chat.type.ChatType;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.type.ChatRoomStatus;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;
import com.teka.shared.constants.WebSocketConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SendChatService implements SendChatUseCase {

    private final ChatRedisPublisher redisPublisher;
    private final FindUserPort findUserPort;
    private final SaveChatPort saveChatPort;
    private final FindChatRoomPort findChatRoomPort;
    private final GoogleCloudTranslationAdapter googleCloudTranslationAdapter;

    @Override
    public void execute(UserId userId, String chatRoomUuid, ChatCommand command) {
        User user = findUserPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        ChatRoom chatRoom = findChatRoomPort.findByUuid(UUID.fromString(chatRoomUuid))
                .orElseThrow(ChatRoomNotFoundException::new);

        validateUser(user, chatRoom);
        Language detectedLanguage = null;

        if (command.type() == ChatType.TEXT) {
            detectedLanguage = googleCloudTranslationAdapter.detectLanguage(command.message());
        }

        Chat chat = saveChatPort.save(
                new Chat(
                        null,
                        user,
                        chatRoom,
                        command.type(),
                        command.message(),
                        detectedLanguage,
                        null,
                        null
                )
        );

        redisPublisher.publish(WebSocketConstant.SUBSCRIBE_ENDPOINT + chatRoomUuid,
                ChatDto.from(chat)
        );
    }

    private void validateUser(User user, ChatRoom chatRoom) {
        if (chatRoom.getStatus() == ChatRoomStatus.CLOSED && user.getType() != UserType.OBSERVER) {
            throw new AuthorityMismatchException();
        }
        if (!(user.getType() == UserType.USER)) {
            throw new AuthorityMismatchException();
        }
        if (!user.getChatRoomId().equals(chatRoom.getId())) {
            throw new UserNotInChatRoomException();
        }
    }
}