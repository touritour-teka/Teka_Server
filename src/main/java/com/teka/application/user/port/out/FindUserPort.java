package com.teka.application.user.port.out;

import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.PhoneNumber;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import com.teka.domain.user.type.Language;

import java.util.Optional;
import java.util.Set;

public interface FindUserPort {
    Optional<User> findById(UserId id);
    Optional<User> findByPhoneNumber(PhoneNumber phoneNumber, ChatRoomId chatRoomId);

    Set<Language> findLanguagesByChatRoomId(ChatRoomId chatRoomId);
}
