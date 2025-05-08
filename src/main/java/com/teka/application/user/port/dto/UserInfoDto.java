package com.teka.application.user.port.dto;

import com.teka.domain.user.Email;
import com.teka.domain.user.PhoneNumber;
import com.teka.domain.user.User;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;

public record UserInfoDto(
        String username,
        PhoneNumber phoneNumber,
        Email email,
        Language language,
        UserType type
) {
    public static UserInfoDto from(User user) {
        return new UserInfoDto(
                user.getUsername(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getLanguage(),
                user.getType()
        );
    }
}
