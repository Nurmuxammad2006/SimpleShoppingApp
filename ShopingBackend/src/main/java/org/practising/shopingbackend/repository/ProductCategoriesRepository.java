package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.ProductCategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategoriesModel, Integer> {
}
