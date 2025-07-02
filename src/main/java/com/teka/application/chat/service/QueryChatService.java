package com.teka.application.chat.service;

import com.teka.adapter.chat.out.translate.GoogleCloudTranslationAdapter;
import com.teka.application.auth.exception.error.AuthorityMismatchException;
import com.teka.application.chat.exception.UserNotInChatRoomException;
import com.teka.application.chat.port.dto.ChatDto;
import com.teka.application.chat.port.in.QueryChatUseCase;
import com.teka.application.chat.port.out.FindChatPort;
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
import com.teka.domain.user.type.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QueryChatService implements QueryChatUseCase {

    private final FindChatPort findChatPort;
    private final FindUserPort findUserPort;
    private final FindChatRoomPort findChatRoomPort;
    private final GoogleCloudTranslationAdapter googleCloudTranslationAdapter;

    @Override
    public List<ChatDto> execute(UserId userId, String chatRoomUuid, Long cursor, int size) {
        User user = findUserPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        ChatRoom chatRoom = findChatRoomPort.findByUuid(UUID.fromString(chatRoomUuid))
                .orElseThrow(ChatRoomNotFoundException::new);

        validateUser(user, chatRoom);

        List<Chat> chatList = findChatPort.findChats(chatRoom, cursor, size);

        List<Chat> toTranslate = chatList.stream()
                .filter(chat -> chat.getType() == ChatType.TEXT && chat.getDetectedLanguage() != user.getLanguage())
                .toList();

        List<String> translatedTextList = googleCloudTranslationAdapter.translateAllTexts(
                user.getLanguage().getCode(),
                toTranslate.stream()
                        .map(Chat::getMessage)
                        .toList()
        );

        List<ChatDto> result = new ArrayList<>();
        int index = 0;

        for (Chat chat : chatList) {
            result.add(toTranslate.contains(chat)
                    ? ChatDto.from(chat, user.getLanguage(), translatedTextList.get(index++)) :
                    ChatDto.from(chat)
            );
        }

        return result;
    }

    private void validateUser(User user, ChatRoom chatRoom) {
        if (!user.getChatRoomId().equals(chatRoom.getId())) {
            throw new UserNotInChatRoomException();
        }

        if (chatRoom.getStatus() == ChatRoomStatus.CLOSED && user.getType() != UserType.OBSERVER) {
            throw new AuthorityMismatchException();
        }
    }
}