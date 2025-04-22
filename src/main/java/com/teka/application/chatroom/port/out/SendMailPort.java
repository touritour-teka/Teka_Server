package com.teka.application.chatroom.port.out;

import com.teka.domain.user.Email;

public interface SendMailPort {
    void send(Email to, String subject, String link);
}
