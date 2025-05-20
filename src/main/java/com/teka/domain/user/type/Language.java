package com.teka.domain.user.type;

import com.teka.shared.property.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language implements EnumProperty {

    KOREAN("한국어", "ko"),
    ENGLISH("영어", "en"),
    CHINESE("중국어", "zh-CN")
    ;

    private final String description;
    private final String code;

    public static Language fromCode(String code) {
        for (Language language : Language.values()) {
            if (language.getCode().equals(code)) {
                return language;
            }
        }
        return null;
    }
}
