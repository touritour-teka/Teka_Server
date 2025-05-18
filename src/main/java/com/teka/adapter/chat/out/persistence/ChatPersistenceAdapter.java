package com.teka.adapter.chat.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import com.teka.adapter.chatroom.out.persistence.ChatRoomRepository;
import com.teka.adapter.user.out.persistence.UserJpaEntity;
import com.teka.adapter.user.out.persistence.UserRepository;
import com.teka.application.chat.port.out.SaveChatPort;
import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.domain.chat.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatPersistenceAdapter implements SaveChatPort {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public Long save(Chat chat) {
        UserJpaEntity user = userRepository.findById(chat.getUser().getId().value())
                .orElseThrow(UserNotFoundException::new);

        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chat.getChatRoom().getId().value())
                .orElseThrow(ChatRoomNotFoundException::new);

        return chatRepository.save(new ChatJpaEntity(user, chatRoom, chat.getMessage())).getId();
    }
}
