package com.teka.adapter.chatroom.out.persistence;

import com.teka.adapter.admin.out.persistence.AdminJpaEntity;
import com.teka.adapter.admin.out.persistence.AdminRepository;
import com.teka.application.chatroom.port.out.CloseChatRoomPort;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.chatroom.port.out.OpenChatRoomPort;
import com.teka.application.chatroom.port.out.SaveChatRoomPort;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ChatRoomPersistenceAdapter implements SaveChatRoomPort, FindChatRoomPort, CloseChatRoomPort, OpenChatRoomPort {

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

    @Transactional
    @Override
    public void close(ChatRoomId chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chatRoomId.value())
                .orElseThrow(EntityNotFoundException::new);
        chatRoom.close();
    }

    @Transactional
    @Override
    public void open(ChatRoomId chatRoomId) {
        ChatRoomJpaEntity chatRoom = chatRoomRepository.findById(chatRoomId.value()).get();
        chatRoom.open();
    }
}
