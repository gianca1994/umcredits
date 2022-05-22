package com.gianca1994.umcredits.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String EMAIL_SENDER;

    @Value("${mail.sender.subject}")
    private String EMAIL_SUBJECT;

    public void sendMail(String to, String username, String activationCode) {
        SimpleMailMessage message = new SimpleMailMessage();

        String textEmail = "Welcome " + username + " to UMCredits. Activation code: " + activationCode;

        message.setFrom(EMAIL_SENDER);
        message.setTo(to);
        message.setSubject(EMAIL_SUBJECT);
        message.setText(textEmail);

        javaMailSender.send(message);

    }
}