package com.teka.adapter.user.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import com.teka.adapter.chatroom.out.persistence.ChatRoomRepository;
import com.teka.application.user.port.out.*;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.PhoneNumber;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements SaveUserPort, CheckUserPort, DeleteUserPort, ChangeUserPort, FindUserPort {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void save(User user) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(user.getChatRoomId().value())
                .orElseThrow(EntityNotFoundException::new);
        userRepository.save(UserJpaEntity.from(user, chatRoom));
    }

    @Override
    public Optional<User> findById(UserId id) {
        return userRepository.findById(id.value())
                .map(UserJpaEntity::toDomain);
    }

    @Override
    public Optional<User> findByPhoneNumber(PhoneNumber phoneNumber, ChatRoomId chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chatRoomId.value())
                .orElseThrow(EntityNotFoundException::new);

        return userRepository.findByPhoneNumberAndChatRoom(phoneNumber.value(), chatRoom)
                .map(UserJpaEntity::toDomain);
    }

    @Override
    public Set<Language> findLanguagesByChatRoomId(ChatRoomId chatRoomId) {
        return userRepository.findLanguagesByChatRoomId(chatRoomId.value());
    }

    @Override
    public boolean existsByEmail(String email, ChatRoomId chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chatRoomId.value())
                .orElseThrow(EntityNotFoundException::new);
        return userRepository.existsByChatRoomAndEmail(chatRoom, email);
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber, ChatRoomId chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chatRoomId.value())
                .orElseThrow(EntityNotFoundException::new);
        return userRepository.existsByChatRoomAndPhoneNumber(chatRoom, phoneNumber.value());
    }

    @Override
    public void changeUserType(UserId userId, UserType type) {
        UserJpaEntity user = userRepository.findById(userId.value())
                .orElseThrow(EntityNotFoundException::new);
        user.changeType(type);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void changeUsername(UserId userId, String username) {
        UserJpaEntity user = userRepository.findById(userId.value())
                .orElseThrow(EntityNotFoundException::new);
        user.setUsername(username);
    }

    @Transactional
    @Override
    public void changeLanguage(UserId userId, Language language) {
        UserJpaEntity user = userRepository.findById(userId.value())
                .orElseThrow(EntityNotFoundException::new);
        user.setLanguage(language);
    }

    @Override
    public void deleteByUserId(UserId userId) {
        userRepository.deleteById(userId.value());
    }
}
