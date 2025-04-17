package com.teka.adapter.chatroom.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomJpaEntity, Long> {
    boolean existsByIdAndAdminId(Long id, Long adminId);
}
