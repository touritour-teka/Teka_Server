package com.teka.domain.chatroom.exception.error;

import com.teka.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatRoomDomainErrorProperty implements ErrorProperty {

    OVER_OPERATING_PERIOD(HttpStatus.FORBIDDEN, "지금은 채팅방 운영 기간이 아닙니다."),
    CHAT_ROOM_ADMIN_MISMATCH(HttpStatus.FORBIDDEN, "해당 채팅방의 관리자가 아닙니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
