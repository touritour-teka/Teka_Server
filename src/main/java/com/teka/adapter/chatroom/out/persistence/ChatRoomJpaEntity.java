package com.teka.adapter.chatroom.out.persistence;

import com.teka.adapter.admin.out.persistence.AdminJpaEntity;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_chat_room")
public class ChatRoomJpaEntity {

    @Column(name = "chat_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Long maxParticipants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="admin_id")
    private AdminJpaEntity admin;

    @Builder
    public ChatRoomJpaEntity(UUID uuid, String name, LocalDate startDate, LocalDate endDate, Long maxParticipants, AdminJpaEntity admin) {
        this.uuid = uuid;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxParticipants = maxParticipants;
        this.admin = admin;
    }

    public ChatRoom toDomain() {
        return ChatRoom.fullBuilder()
                .id(new ChatRoomId(id))
                .uuid(uuid)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .maxParticipants(maxParticipants)
                .adminId(admin.toDomain().getId())
                .build();
    }

    public static ChatRoomJpaEntity from(ChatRoom chatRoom, AdminJpaEntity admin) {
        return ChatRoomJpaEntity.builder()
                .uuid(chatRoom.getUuid())
                .name(chatRoom.getName())
                .startDate(chatRoom.getStartDate())
                .endDate(chatRoom.getEndDate())
                .maxParticipants(chatRoom.getMaxParticipants())
                .admin(admin)
                .build();
    }
}
