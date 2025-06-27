package com.teka.application.chat.port.in;

import com.teka.domain.user.UserId;

public interface ChatSubValidationUseCase {
    void execute(UserId userId, String chatRoomUuid);
}
