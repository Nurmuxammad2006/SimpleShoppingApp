package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.AuthModel;
import org.practising.shopingbackend.model.ForgotPasswordResetTokensModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ForgotPasswordResetTokenRepository extends JpaRepository<ForgotPasswordResetTokensModel, Integer> {
    Optional<ForgotPasswordResetTokensModel> findByToken(String token);
    void deleteByUser(AuthModel authModel);
}