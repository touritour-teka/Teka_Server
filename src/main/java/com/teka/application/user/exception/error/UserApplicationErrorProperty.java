package com.teka.application.user.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserApplicationErrorProperty implements ErrorProperty {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    PHONE_NUMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 전화번호입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
