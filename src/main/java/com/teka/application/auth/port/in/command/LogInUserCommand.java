package com.teka.application.auth.port.in.command;

import com.teka.domain.user.type.Language;

public record LogInUserCommand(
        String phoneNumber,
        String name,
        Language language
) {
}
