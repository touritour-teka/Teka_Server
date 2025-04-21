package com.teka.domain.user.type;

import com.teka.shared.property.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language implements EnumProperty {

    KOREAN("한국어"),
    ENGLISH("영어"),
    CHINESE("중국어")
    ;

    private final String description;
}
