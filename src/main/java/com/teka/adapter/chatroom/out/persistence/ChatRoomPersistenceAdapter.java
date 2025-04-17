package com.teka.adapter.chatroom.out.persistence;

import com.teka.adapter.admin.out.persistence.AdminJpaEntity;
import com.teka.adapter.admin.out.persistence.AdminRepository;
import com.teka.application.chatroom.port.out.CloseChatRoomPort;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.chatroom.port.out.SaveChatRoomPort;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.chatroom.type.ChatRoomStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ChatRoomPersistenceAdapter implements SaveChatRoomPort, FindChatRoomPort, CloseChatRoomPort {

    private final ChatRoomRepository chatRoomRepository;
    private final AdminRepository adminRepository;

    @Override
    public Long save(ChatRoom chatRoom) {
        AdminJpaEntity admin = adminRepository.findById(chatRoom.getAdminId().value())
                        .orElseThrow(EntityNotFoundException::new);
        return chatRoomRepository.save(ChatRoomJpaEntity.from(chatRoom, admin)).getId();
    }

    @Override
    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll().stream()
                .map(ChatRoomJpaEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<ChatRoom> findById(ChatRoomId chatRoomId) {
        return chatRoomRepository.findById(chatRoomId.value())
                .map(ChatRoomJpaEntity::toDomain);
    }

    @Override
    public void close(ChatRoomId chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chatRoomId.value())
                .orElseThrow(EntityNotFoundException::new);
        chatRoom.close();
        chatRoomRepository.save(chatRoom);
    }
}
