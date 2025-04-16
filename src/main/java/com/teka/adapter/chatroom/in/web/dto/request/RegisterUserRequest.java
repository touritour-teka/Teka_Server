package com.teka.adapter.chatroom.in.web.dto.request;

import com.teka.application.chatroom.port.in.command.RegisterUserCommand;
import com.teka.domain.user.type.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(

        @NotBlank(message = "필수값입니다.")
        @Size(min = 11, max = 11, message = "11자여야합니다.")
        String phoneNumber,

        @NotBlank(message = "필수값입니다.")
        @Size(max = 255, message = "255자 이하여야 합니다.")
        @Email(message = "이메일 형식이여야 합니다.")
        String email,

        @NotNull(message = "필수값입니다.")
        UserType type
) {
    public RegisterUserCommand toCommand() {
        return RegisterUserCommand.builder()
                .phoneNumber(this.phoneNumber)
                .email(this.email)
                .type(this.type)
                .build();
    }
}
