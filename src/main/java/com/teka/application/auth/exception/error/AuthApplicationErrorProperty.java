package com.teka.application.auth.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthApplicationErrorProperty implements ErrorProperty {

    WRONG_LOGIN(HttpStatus.UNAUTHORIZED, "사용자가 또는 비밀번호가 일치하지 않습니다."),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
