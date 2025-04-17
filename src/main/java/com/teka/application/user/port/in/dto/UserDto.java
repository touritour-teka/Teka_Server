package com.teka.application.user.port.in.dto;

import com.teka.domain.user.Email;
import com.teka.domain.user.PhoneNumber;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;

public record UserDto(
        UserId id,
        String username,
        PhoneNumber phoneNumber,
        Email email,
        Language language,
        UserType type
) {
    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getLanguage(),
                user.getType()
        );
    }
}
