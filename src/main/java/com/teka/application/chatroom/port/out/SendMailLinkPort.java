package com.teka.application.chatroom.port.out;

import com.teka.domain.user.Email;

public interface SendMailLinkPort {
    void send(Email to, String subject, String chatRoomName, String link);
}
