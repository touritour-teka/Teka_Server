package com.teka.application.chat.service;

import com.teka.adapter.chat.out.redis.ChatRedisPublisher;
import com.teka.adapter.chat.out.translate.GoogleCloudTranslationAdapter;
import com.teka.application.chat.exception.UserNotInChatRoomException;
import com.teka.application.chat.port.dto.ChatDto;
import com.teka.application.chat.port.dto.SenderDto;
import com.teka.application.chat.port.in.ChatMessageUseCase;
import com.teka.application.chat.port.in.command.ChatCommand;
import com.teka.application.chat.port.out.SaveChatPort;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.chat.Chat;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import com.teka.domain.user.type.Language;
import com.teka.shared.constants.WebSocketConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChatMessageService implements ChatMessageUseCase {

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

        Language language = googleCloudTranslationAdapter.detectLanguage(command.message());
        Chat chat = saveChatPort.save(
                new Chat(
                        null,
                        user,
                        chatRoom,
                        command.message(),
                        language,
                        null,
                        null
                )
        );

        redisPublisher.publish(WebSocketConstant.SUBSCRIBE_ENDPOINT + chatRoomUuid,
                new ChatDto(
                        chat.getId().value(),
                        chat.getChatRoom().getUuid().toString(),
                        SenderDto.from(chat.getUser()),
                        chat.getMessage(),
                        chat.getDetectedLanguage(),
                        chat.getCreatedAt(),
                        chat.getUpdatedAt()
                )
        );
    }

    private void validateUser(User user, ChatRoom chatRoom) {
        if (!user.getChatRoomId().equals(chatRoom.getId())) {
            throw new UserNotInChatRoomException();
        }
    }
}