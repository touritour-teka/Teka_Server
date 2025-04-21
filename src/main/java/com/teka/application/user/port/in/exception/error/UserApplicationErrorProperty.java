package com.teka.application.user.port.in.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserApplicationErrorProperty implements ErrorProperty {

    PHONE_NUMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 전화번호입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
