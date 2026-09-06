package com.laudado.talentforgeaibackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Value("${app.password-reset-path}")
    private String passwordResetPath;

    @Value("${app.email-verification-path}")
    private String emailVerificationPath;


    public void sendPasswordResetEmail(
            String recipientEmail,
            String resetToken
    ) {

        String resetLink = frontendUrl
                + passwordResetPath
                + "?token="
                + resetToken;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Reset Your TalentForge Password");

        message.setText(
                "Hello,\n\n" +
                        "We received a request to reset your TalentForge password.\n\n" +
                        "Click the link below to reset your password:\n\n" +
                        resetLink +
                        "\n\n" +
                        "This link will expire in 15 minutes.\n\n" +
                        "If you did not request a password reset, you can safely ignore this email.\n\n" +
                        "Regards,\n" +
                        "TalentForge Team"
        );

        mailSender.send(message);
    }


    public void sendEmailVerificationEmail(
            String recipientEmail,
            String verificationToken
    ) {

        String verificationLink = frontendUrl
                + emailVerificationPath
                + "?token="
                + verificationToken;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Verify Your TalentForge Email");

        message.setText(
                "Hello,\n\n" +
                        "Welcome to TalentForge!\n\n" +
                        "Please verify your email address by clicking the link below:\n\n" +
                        verificationLink +
                        "\n\n" +
                        "This verification link will expire in 15 minutes.\n\n" +
                        "If you did not create a TalentForge account, you can safely ignore this email.\n\n" +
                        "Regards,\n" +
                        "TalentForge Team"
        );

        mailSender.send(message);
    }
}