package com.teka.adapter.chatroom.out.persistence;

import com.teka.adapter.admin.out.persistence.AdminJpaEntity;
import com.teka.adapter.user.out.persistence.UserJpaEntity;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.chatroom.type.ChatRoomStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ChatRoomStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="admin_id")
    private AdminJpaEntity admin;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private final List<UserJpaEntity> userList = new ArrayList<>();

    @Builder
    public ChatRoomJpaEntity(UUID uuid, String name, LocalDate startDate, LocalDate endDate, Long maxParticipants, AdminJpaEntity admin, ChatRoomStatus status) {
        this.uuid = uuid;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxParticipants = maxParticipants;
        this.status = status;
        this.admin = admin;
    }

    public void close() {
        this.status = ChatRoomStatus.CLOSED;
    }

    public void open() {
        this.status = ChatRoomStatus.OPEN;
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
                .status(status)
                .userList(userList.stream().map(UserJpaEntity::toDomain).toList())
                .build();
    }

    public static ChatRoomJpaEntity from(ChatRoom chatRoom, AdminJpaEntity admin) {
        return ChatRoomJpaEntity.builder()
                .uuid(chatRoom.getUuid())
                .name(chatRoom.getName())
                .startDate(chatRoom.getStartDate())
                .endDate(chatRoom.getEndDate())
                .maxParticipants(chatRoom.getMaxParticipants())
                .status(chatRoom.getStatus())
                .admin(admin)
                .build();
    }
}
