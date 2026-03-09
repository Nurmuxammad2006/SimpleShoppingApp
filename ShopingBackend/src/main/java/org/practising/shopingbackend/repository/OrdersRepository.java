package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrdersModel, Integer> {
}
