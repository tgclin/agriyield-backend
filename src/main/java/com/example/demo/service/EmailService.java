package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendPasswordResetEmail(String toEmail, String resetLink) throws MailException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(senderEmail);
            message.setTo(toEmail);
            message.setSubject("AgriYield Engine - Password Reset Request");
            message.setText("Hello,\n\n" +
                    "You requested a password reset for your AgriYield Engine account.\n\n" +
                    "Click the link below to set a new password (link valid for 15 minutes):\n" +
                    resetLink + "\n\n" +
                    "If you did not request this, you can safely ignore this email.");

            mailSender.send(message);
        } catch (MailException e) {
            // Log the error for internal debugging
            System.err.println("Failed to send email to " + toEmail + ": " + e.getMessage());
            throw e; // Re-throw to be handled by controller or global exception handler
        }
    }
}