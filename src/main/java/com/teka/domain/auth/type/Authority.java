package com.teka.domain.auth.type;

import com.teka.shared.property.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority implements EnumProperty {
    USER("유저"),
    ADMIN("어드민")
    ;

    private final String description;
}
