package com.teka.application.chat.port.dto;

import com.teka.domain.user.User;
import com.teka.domain.user.UserId;

public record SenderDto(
        UserId id,
        String username
) {
    public static SenderDto from(User user) {
        return new SenderDto(user.getId(), user.getUsername());
    }
}
