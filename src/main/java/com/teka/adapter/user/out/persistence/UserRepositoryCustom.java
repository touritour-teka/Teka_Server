package com.teka.adapter.user.out.persistence;

import com.teka.domain.user.type.Language;

import java.util.Set;

public interface UserRepositoryCustom {
    Set<Language> findLanguagesByChatRoomId(Long chatRoomId);
}
