package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.AuthModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthModel, Integer> {
    boolean existsByEmail(String email);
    Optional<AuthModel> findByEmail(String email);
}
