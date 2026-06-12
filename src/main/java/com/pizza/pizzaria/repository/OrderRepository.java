package com.pizza.pizzaria.repository;

import com.pizza.pizzaria.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
