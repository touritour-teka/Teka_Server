package com.teka.application.chat.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatApplicationErrorProperty implements ErrorProperty {

    USER_NOT_IN_CHAT_ROOM(HttpStatus.FORBIDDEN, "소속되지 않은 채팅방입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
