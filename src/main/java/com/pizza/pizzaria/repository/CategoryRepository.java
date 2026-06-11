package com.pizza.pizzaria.repository;

import com.pizza.pizzaria.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
