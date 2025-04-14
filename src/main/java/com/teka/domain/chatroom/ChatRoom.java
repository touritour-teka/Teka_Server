package com.teka.domain.chatroom;

import com.teka.domain.admin.AdminId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class ChatRoom {

    private final ChatRoomId id;

    private final UUID uuid;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long maxParticipants;

    private final AdminId adminId;

    @Builder
    public ChatRoom(ChatRoomId id, String name, LocalDate startDate, LocalDate endDate, Long maxParticipants, AdminId adminId) {
        this.id = id;
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxParticipants = maxParticipants;
        this.adminId = adminId;
    }
}
