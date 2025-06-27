package com.teka.application.chat.port.out;

import com.teka.domain.chat.Chat;

public interface SaveChatPort {
    Chat save(Chat chat);
}
