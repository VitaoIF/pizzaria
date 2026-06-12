package com.pizza.pizzaria.repository;

import com.pizza.pizzaria.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
