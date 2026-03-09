package org.practising.shopingbackend.service;

import org.practising.shopingbackend.model.AuthModel;
import org.practising.shopingbackend.model.ForgotPasswordResetTokensModel;
import org.practising.shopingbackend.repository.AuthRepository;
import org.practising.shopingbackend.repository.ForgotPasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<?> sendEmail(String email) {

        Optional<AuthModel> user = authRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Email not found"));
        }

        ForgotPasswordResetTokensModel resetTokensModel = new ForgotPasswordResetTokensModel(user.get());
        forgotPasswordResetTokenRepository.save(resetTokensModel);

        String resetLink = "http://localhost:8080/reset-password?token=" + resetTokensModel.getToken();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nurimuhammad7473@gmail.com");
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Click the link to reset your password:\n" + resetLink + "\n\nExpires in 15 minutes.");

        mailSender.send(message);

        return ResponseEntity.ok(Map.of("message", "Password reset email sent"));
    }
}