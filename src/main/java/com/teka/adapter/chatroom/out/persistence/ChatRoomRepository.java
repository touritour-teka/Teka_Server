package com.teka.adapter.chatroom.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoomJpaEntity, Long> {

    List<ChatRoomJpaEntity> findByAdminId(Long adminId);
    Optional<ChatRoomJpaEntity> findByUuid(UUID uuid);
}
