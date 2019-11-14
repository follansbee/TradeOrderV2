package com.follansbee.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(String to, String subject, String body) throws MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(to);
        helper.setFrom("dave.follansbee@gmail.com");
        helper.setText(body);
        helper.setSubject(subject);

        sender.send(message);
    }
}
