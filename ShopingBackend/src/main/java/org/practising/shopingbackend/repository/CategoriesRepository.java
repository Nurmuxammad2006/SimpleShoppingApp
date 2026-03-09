package org.practising.shopingbackend.repository;

import org.practising.shopingbackend.model.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<CategoriesModel, Integer> {
}
