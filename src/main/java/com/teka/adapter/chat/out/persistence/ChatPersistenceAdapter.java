package com.teka.adapter.chat.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import com.teka.adapter.chatroom.out.persistence.ChatRoomRepository;
import com.teka.adapter.user.out.persistence.UserJpaEntity;
import com.teka.adapter.user.out.persistence.UserRepository;
import com.teka.application.chat.port.out.FindChatPort;
import com.teka.application.chat.port.out.SaveChatPort;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.domain.chat.Chat;
import com.teka.domain.chatroom.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChatPersistenceAdapter implements SaveChatPort, FindChatPort {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public Chat save(Chat chat) {
        UserJpaEntity user = userRepository.findById(chat.getUser().getId().value())
                .orElseThrow(UserNotFoundException::new);

        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chat.getChatRoom().getId().value())
                .orElseThrow(ChatRoomNotFoundException::new);

        return chatRepository.save(ChatJpaEntity.from(chat, user, chatRoom)).toDomain();
    }

    @Override
    public List<Chat> findChats(ChatRoom chatRoom, Long cursor, int size) {
        return chatRepository.findChats(chatRoom, cursor, size).stream()
                .map(ChatJpaEntity::toDomain)
                .toList();
    }
}
