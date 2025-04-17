package com.teka.domain.chatroom;

import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.type.ChatRoomStatus;
import com.teka.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
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

    private ChatRoomStatus status;

    private List<User> userList;

    @Builder(builderClassName = "ChatRoomBasicBuilder", builderMethodName = "basicBuilder")
    public ChatRoom(ChatRoomId id, String name, LocalDate startDate, LocalDate endDate, Long maxParticipants, AdminId adminId) {
        this.id = id;
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxParticipants = maxParticipants;
        this.adminId = adminId;
        this.status = ChatRoomStatus.OPEN;
    }

    @Builder(builderClassName = "ChatRoomFullBuilder", builderMethodName = "fullBuilder")
    public ChatRoom(ChatRoomId id, UUID uuid, String name, LocalDate startDate, LocalDate endDate, Long maxParticipants, AdminId adminId, ChatRoomStatus status, List<User> userList) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxParticipants = maxParticipants;
        this.adminId = adminId;
        this.status = status;
        this.userList = userList;
    }
}
