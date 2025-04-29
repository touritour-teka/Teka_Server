package com.teka.application.chatroom.port.out;

import com.teka.domain.user.Email;

public interface SendMailLinkPort {
    void sendEmail(Email to, String subject, String chatRoomName, String link);
}
