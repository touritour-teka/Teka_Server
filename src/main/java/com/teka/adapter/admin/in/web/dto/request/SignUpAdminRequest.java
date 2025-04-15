package com.teka.adapter.admin.in.web.dto.request;

import com.teka.application.admin.port.in.command.SignUpAdminCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpAdminRequest(
        @NotBlank
        @Size(max = 30, message = "30자 이하여야 합니다.")
        String username,

        @NotBlank
        @Size(max = 20, message = "20자 이하여야 합니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 최소 하나의 문자, 숫자, 특수문자를 포함하며 8 글자 이상이어야 합니다.")
        String password
) {
    public SignUpAdminCommand toCommand() {
        return new SignUpAdminCommand(username, password);
    }
}
