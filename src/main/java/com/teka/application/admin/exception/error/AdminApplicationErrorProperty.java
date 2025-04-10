package com.teka.application.admin.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AdminApplicationErrorProperty implements ErrorProperty {

    DUPLICATE_ADMIN_ID(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다.");

    private final HttpStatus status;
    private final String message;
}
