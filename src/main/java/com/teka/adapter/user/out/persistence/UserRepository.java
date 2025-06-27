package com.teka.adapter.user.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long>, UserRepositoryCustom {

    Optional<UserJpaEntity> findByPhoneNumberAndChatRoom(String phoneNumber, ChatRoomJpaEntity chatRoom);
    boolean existsByChatRoomAndPhoneNumber(ChatRoomJpaEntity chatRoom, String phoneNumber);
    boolean existsByChatRoomAndEmail(ChatRoomJpaEntity chatRoom, String email);
}
