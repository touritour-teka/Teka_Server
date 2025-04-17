package com.teka.adapter.chatroom.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoomJpaEntity, Long> {
    List<ChatRoomJpaEntity> findByAdminId(Long adminId);
    boolean existsByIdAndAdminId(Long id, Long adminId);
}
