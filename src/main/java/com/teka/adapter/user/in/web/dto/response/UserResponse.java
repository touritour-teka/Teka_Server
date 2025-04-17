package com.teka.adapter.user.in.web.dto.response;

import com.teka.application.user.port.in.dto.UserDto;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;

public record UserResponse(
        Long id,
        String username,
        String phoneNumber,
        String email,
        Language language,
        UserType type
) {
    public static UserResponse from(UserDto userDto) {
        return new UserResponse(
                userDto.id().value(),
                userDto.username(),
                userDto.phoneNumber().value(),
                userDto.email().value(),
                userDto.language(),
                userDto.type()
        );
    }
}
