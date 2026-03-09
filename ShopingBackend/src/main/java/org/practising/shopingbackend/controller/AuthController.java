package org.practising.shopingbackend.controller;

import org.practising.shopingbackend.dto.ErrorResponse;
import org.practising.shopingbackend.dto.ForgotPasswordRequest;
import org.practising.shopingbackend.dto.Login;
import org.practising.shopingbackend.model.AuthModel;
import org.practising.shopingbackend.service.AuthService;
import org.practising.shopingbackend.service.EmailAlreadyExistsException;
import org.practising.shopingbackend.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthService authService;
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setForgotPasswordService(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthModel authModel) {
        try {
            return ResponseEntity.ok(authService.save(authModel));
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email already exists!"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login userLogin) {
        AuthModel user = authService.login(userLogin.getEmail(), userLogin.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return forgotPasswordService.sendEmail(request.getEmail());
    }
}