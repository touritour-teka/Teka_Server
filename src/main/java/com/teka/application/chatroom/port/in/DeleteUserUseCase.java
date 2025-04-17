package com.teka.application.chatroom.port.in;

import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.UserId;

import java.util.List;

public interface DeleteUserUseCase {
    void execute(List<UserId> userIdList, ChatRoomId chatRoomId, AdminId adminId);
}
