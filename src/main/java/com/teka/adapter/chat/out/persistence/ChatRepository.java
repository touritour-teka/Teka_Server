package com.teka.adapter.chat.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatJpaEntity, Long> {
}
