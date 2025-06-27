package com.teka.domain.user.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserDomainErrorProperty implements ErrorProperty {

    PHONE_NUMBER_FORMAT_MISMATCH(HttpStatus.BAD_REQUEST, "전화번호는 11자리여야합니다."),
    EMAIL_FORMAT_MISMATCH(HttpStatus.BAD_REQUEST, "이메일 형식이 아닙니다."),
    USERNAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "이름은 30글자 이하여야 합니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
