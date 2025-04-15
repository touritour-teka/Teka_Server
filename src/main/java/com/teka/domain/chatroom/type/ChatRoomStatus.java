package com.teka.domain.chatroom.type;

import com.teka.shared.property.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatRoomStatus implements EnumProperty {
    OPEN("운영중"),
    CLOSED("종료")
    ;

    private final String description;
}
