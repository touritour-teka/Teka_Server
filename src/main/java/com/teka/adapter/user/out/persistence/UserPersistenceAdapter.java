package com.teka.adapter.user.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import com.teka.adapter.chatroom.out.persistence.ChatRoomRepository;
import com.teka.application.user.port.out.CheckUserEmailPort;
import com.teka.application.user.port.out.CheckUserPhoneNumberPort;
import com.teka.application.user.port.out.SaveUserPort;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements SaveUserPort, CheckUserPhoneNumberPort, CheckUserEmailPort {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void save(User user) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(user.getChatRoomId().value())
                .orElseThrow(EntityNotFoundException::new);
        userRepository.save(UserJpaEntity.from(user, chatRoom));
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber, ChatRoomId chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chatRoomId.value())
                .orElseThrow(EntityNotFoundException::new);
        return userRepository.existsByChatRoomAndPhoneNumber(chatRoom, phoneNumber);
    }

    @Override
    public boolean existsByEmail(String email, ChatRoomId chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chatRoomId.value())
                .orElseThrow(EntityNotFoundException::new);
        return userRepository.existsByChatRoomAndEmail(chatRoom, email);
    }
}
