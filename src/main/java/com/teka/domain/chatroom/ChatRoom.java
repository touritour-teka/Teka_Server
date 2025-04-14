package com.teka.domain.chatroom;

import com.teka.domain.admin.AdminId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ChatRoom {

    private final ChatRoomId id;

    private final String uuid;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long maxParticipants;

    private final AdminId adminId;
}
