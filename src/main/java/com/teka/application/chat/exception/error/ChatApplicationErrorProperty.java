package com.teka.application.chat.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatApplicationErrorProperty implements ErrorProperty {

    USER_NOT_IN_CHAT_ROOM(HttpStatus.FORBIDDEN, "소속되지 않은 채팅방입니다."),
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "파일이 비었습니다."),
    FILE_SIZE_LIMIT_EXCEEDED(HttpStatus.PAYLOAD_TOO_LARGE, "최대 5MB의 파일까지만 업로드할 수 있습니다."),
    MEDIA_TYPE_MISMATCH(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 파일 형식입니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
