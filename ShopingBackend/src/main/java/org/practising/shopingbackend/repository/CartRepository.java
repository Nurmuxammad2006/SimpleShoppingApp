package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Integer> {

    // Find all cart items for a specific user
    List<CartModel> findByUserId(Integer userId);

    // Find specific cart item by user and product
    Optional<CartModel> findByUserIdAndProductId(Integer userId, Integer productId);

    // Delete all cart items for a user (used during checkout)
    @Modifying
    @Transactional
    @Query("DELETE FROM CartModel c WHERE c.userId = :userId")
    void deleteByUserId(@Param("userId") Integer userId);
}