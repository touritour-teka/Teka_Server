package com.teka.domain.chat;

import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.User;
import com.teka.domain.user.type.Language;
import com.teka.shared.domain.AuditableDomain;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Chat extends AuditableDomain {

    private final ChatId id;

    private final User user;

    private final ChatRoom chatRoom;

    private final String message;

    private final Language detectedLanguage;

    @Builder
    public Chat(ChatId id, User user, ChatRoom chatRoom, String message, Language detectedLanguage, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.user = user;
        this.chatRoom = chatRoom;
        this.message = message;
        this.detectedLanguage = detectedLanguage;
    }
}
