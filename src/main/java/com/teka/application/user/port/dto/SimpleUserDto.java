package com.teka.application.user.port.dto;

import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;

public record SimpleUserDto(
        UserId id,
        String username,
        Language language,
        UserType type
) {
    public static SimpleUserDto from(User user) {
        return new SimpleUserDto(
                user.getId(),
                user.getUsername(),
                user.getLanguage(),
                user.getType()
        );
    }
}
