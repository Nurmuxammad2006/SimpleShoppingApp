package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.OrderItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemsModel, Integer> {
}
