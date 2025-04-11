package com.teka.adapter.auth.in.web.dto.request;

import com.teka.application.auth.port.in.command.LogInAdminCommand;
import jakarta.validation.constraints.NotBlank;

public record LogInAdminRequest(
        @NotBlank(message = "필수값입니다.")
        String username,

        @NotBlank(message = "필수값입니다.")
        String password
) {
        public LogInAdminCommand toCommand() {
                return new LogInAdminCommand(username, password);
        }
}
