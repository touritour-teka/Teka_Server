package com.teka.adapter.user.in.web.dto.response;

import com.teka.application.user.port.dto.SimpleUserDto;
import com.teka.application.user.port.dto.UserDto;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;

public record SimpleUserResponse(
        Long id,
        String username,
        Language language,
        UserType type
) {
    public static SimpleUserResponse from(SimpleUserDto dto) {
        return new SimpleUserResponse(
                dto.id().value(),
                dto.username(),
                dto.language(),
                dto.type()
        );
    }

    public static SimpleUserResponse from(UserDto dto) {
        return new SimpleUserResponse(
                dto.id().value(),
                dto.username(),
                dto.language(),
                dto.type()
        );
    }
}
