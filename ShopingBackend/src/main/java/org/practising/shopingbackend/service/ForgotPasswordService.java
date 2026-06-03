package org.practising.shopingbackend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.practising.shopingbackend.model.AuthModel;
import org.practising.shopingbackend.model.ForgotPasswordResetTokensModel;
import org.practising.shopingbackend.repository.AuthRepository;
import org.practising.shopingbackend.repository.ForgotPasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Service
public class ForgotPasswordService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ForgotPasswordResetTokenRepository forgotPasswordResetTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    public ResponseEntity<?> sendEmail(String email) throws MessagingException, IOException {

        Optional<AuthModel> user = authRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Email not found"));
        }

        ForgotPasswordResetTokensModel resetTokensModel = new ForgotPasswordResetTokensModel(user.get());
        forgotPasswordResetTokenRepository.save(resetTokensModel);

        String resetLink = "http://127.0.0.1:5500/shop-full.html?token=" + resetTokensModel.getToken();

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper wrappingMessage = new MimeMessageHelper(message, true, "UTF-8");


        wrappingMessage.setFrom("nurimuhammad7473@gmail.com");
        wrappingMessage.setTo(email);
        wrappingMessage.setSubject("Password Reset Request");

        ClassPathResource resource = new ClassPathResource("templates/emails/password-reset.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        html = html.replace("{{firstName}}", user.get().getFirstName());
        html = html.replace("{{resetLink}}", resetLink);

        wrappingMessage.setText(html, true);

        mailSender.send(message);

        return ResponseEntity.ok(Map.of("message", "Password reset email sent"));
    }
}