package com.teka.domain.user.type;

import com.teka.shared.property.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType implements EnumProperty {

    USER("일반 사용자"),
    OBSERVER("옵저버"),
    ;

    private final String description;
}
