package com.teka.adapter.user.in.web.dto.response;

import com.teka.application.user.port.dto.UserInfoDto;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;

public record UserInfoResponse(
        String username,
        String phoneNumber,
        String email,
        Language language,
        UserType type
) {
    public static UserInfoResponse from(UserInfoDto userInfoDto) {
        return new UserInfoResponse(
                userInfoDto.username(),
                userInfoDto.phoneNumber().value(),
                userInfoDto.email().value(),
                userInfoDto.language(),
                userInfoDto.type()
        );
    }
}
