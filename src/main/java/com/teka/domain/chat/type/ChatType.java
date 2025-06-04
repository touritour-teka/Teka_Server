package com.teka.domain.chat.type;

import com.teka.shared.property.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatType implements EnumProperty {
    TEXT("텍스트"),
    IMAGE("이미지");

    private final String description;
}
