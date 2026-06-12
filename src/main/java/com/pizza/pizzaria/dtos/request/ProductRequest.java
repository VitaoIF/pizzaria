package com.pizza.pizzaria.dtos.request;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        String banner,
        BigDecimal price,
        boolean active,
        Long categoryId
) {
}
