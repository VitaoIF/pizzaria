package com.pizza.pizzaria.dtos.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        Long id,
        String name,
        String description,
        String banner,
        BigDecimal price,
        boolean active,
        Long categoryId,
        String categoryName
) {
}
