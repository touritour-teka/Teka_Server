package com.teka.adapter.user.in.web.dto.request;

import com.teka.application.user.port.in.command.ChangeLanguageCommand;
import com.teka.domain.user.type.Language;
import jakarta.validation.constraints.NotNull;

public record ChangeLanguageRequest(
        @NotNull(message = "필수값입니다.")
        Language language
) {
        public ChangeLanguageCommand toCommand() {
                return new ChangeLanguageCommand(language);
        }
}
