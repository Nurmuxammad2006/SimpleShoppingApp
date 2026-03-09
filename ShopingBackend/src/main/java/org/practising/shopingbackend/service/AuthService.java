package org.practising.shopingbackend.service;

import org.practising.shopingbackend.model.AuthModel;
import org.practising.shopingbackend.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AuthModel save(AuthModel authModel) throws EmailAlreadyExistsException {

        if (authRepository.existsByEmail(authModel.getEmail())) {
            throw new EmailAlreadyExistsException("Email " + authModel.getEmail() + " is already registered");
        }
        authModel.setPassword(passwordEncoder.encode(authModel.getPassword()));

        try{
            return authRepository.save(authModel);
        } catch (DataIntegrityViolationException ex){
            throw new EmailAlreadyExistsException("Email " + authModel.getEmail() + " is already registered");
        }
    }

    public AuthModel login(String email, String password) {
        Optional<AuthModel> user = authRepository.findByEmail(email);

        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return user.get();
            }
        }
        return null;
    }

    public List<AuthModel> findAll() {
        return authRepository.findAll();
    }
}
