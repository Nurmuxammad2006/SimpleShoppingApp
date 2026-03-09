package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductsModel, Integer> {
}
