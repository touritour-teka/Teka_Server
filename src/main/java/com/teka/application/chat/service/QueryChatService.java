package com.teka.application.chat.service;

import com.teka.adapter.chat.out.translate.GoogleCloudTranslationAdapter;
import com.teka.application.chat.exception.UserNotInChatRoomException;
import com.teka.application.chat.port.dto.ChatDto;
import com.teka.application.chat.port.in.QueryChatUseCase;
import com.teka.application.chat.port.out.FindChatPort;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.chat.Chat;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

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
        List<String> translatedTextList = googleCloudTranslationAdapter.translateAllTexts(
                user.getLanguage().getCode(),
                chatList.stream()
                        .map(Chat::getMessage)
                        .toList()
        );

        return IntStream.range(0, chatList.size())
                .mapToObj(i -> ChatDto.from(chatList.get(i), user.getLanguage(), translatedTextList.get(i)))
                .toList();
    }

    private void validateUser(User user, ChatRoom chatRoom) {
        if (!user.getChatRoomId().equals(chatRoom.getId())) {
            throw new UserNotInChatRoomException();
        }
    }
}
