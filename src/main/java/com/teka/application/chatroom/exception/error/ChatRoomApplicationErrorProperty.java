package com.teka.application.chatroom.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatRoomApplicationErrorProperty implements ErrorProperty {

    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "채팅방을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
