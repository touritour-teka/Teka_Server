package com.teka.application.user.port.in.command;

import com.teka.domain.user.type.Language;

public record ChangeLanguageCommand(
        Language language
) {
}
