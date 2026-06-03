package org.practising.shopingbackend.service;

import org.practising.shopingbackend.model.ForgotPasswordResetTokensModel;
import org.practising.shopingbackend.repository.AuthRepository;
import org.practising.shopingbackend.repository.ForgotPasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResetPasswordService {

    @Autowired
    private ForgotPasswordResetTokenRepository forgotPasswordResetTokenRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> validateToken (String token){
        Optional<ForgotPasswordResetTokensModel> resetTokensModel = forgotPasswordResetTokenRepository.findByToken(token);

        if (resetTokensModel.isEmpty()){
            
        }

        return null;
    }

    public ResponseEntity<?> resetPassword(){

        return null;
    }
}
