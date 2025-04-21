package com.teka.adapter.user.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    boolean existsByChatRoomAndPhoneNumber(ChatRoomJpaEntity chatRoom, String phoneNumber);
    boolean existsByChatRoomAndEmail(ChatRoomJpaEntity chatRoom, String email);
}
