package com.teka.domain.user;

import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final UserId id;

    private String username;

    private PhoneNumber phoneNumber;

    private Email email;

    private Language language;

    private final UserType type;

    private final ChatRoomId chatRoomId;

    @Builder
    public User(UserId id, String username, PhoneNumber phoneNumber, Email email, Language language, UserType type, ChatRoomId chatRoomId) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.language = language;
        this.type = type;
        this.chatRoomId = chatRoomId;
    }
}
