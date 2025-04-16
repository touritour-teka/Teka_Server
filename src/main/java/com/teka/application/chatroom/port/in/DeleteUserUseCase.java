package com.teka.application.chatroom.port.in;

import com.teka.domain.user.UserId;

import java.util.List;

public interface DeleteUserUseCase {
    void execute(List<UserId> userIdList);
}
