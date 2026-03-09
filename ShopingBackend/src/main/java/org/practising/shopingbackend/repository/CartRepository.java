package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartModel, Integer> {
}
