package com.teka.adapter.auth.in.web.dto.request;

import com.teka.application.auth.port.in.command.LogInUserCommand;
import com.teka.domain.user.type.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LogInUserRequest(
        @NotBlank(message = "필수값입니다.")
        @Size(min = 11, max = 11, message = "11글자여야 합니다.")
        String phoneNumber,

        @NotBlank(message = "필수값입니다.")
        @Size(max = 30, message = "30글자 이하여야 합니다")
        String username,

        @NotNull(message = "필수값입니다.")
        Language language
) {
    public LogInUserCommand toCommand() {
        return new LogInUserCommand(phoneNumber, username, language);
    }
}
