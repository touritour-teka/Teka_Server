package com.teka.adapter.chatroom.out.persistence;

import com.teka.adapter.admin.out.persistence.AdminJpaEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_chat_room")
public class ChatRoomJpaEntity {

    @Column(name = "chat_room_id")
    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
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
}
