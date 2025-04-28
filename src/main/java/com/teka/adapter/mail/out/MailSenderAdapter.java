package com.teka.adapter.mail.out;

import com.teka.application.chatroom.port.out.SendMailPort;
import com.teka.domain.user.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
public class MailSenderAdapter implements SendMailPort {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void send(Email to, String subject, String chatRoomName, String link) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(to.value());
            helper.setSubject(subject);
            helper.setText(setContext(chatRoomName, link), true);
            helper.setFrom(from);

            javaMailSender.send(message);
        } catch(MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String setContext(String chatRoomName, String link) {
        Context context = new Context();
        context.setVariable("chatRoomName", chatRoomName);
        context.setVariable("link", link);
        return templateEngine.process("mail", context);
    }
}
