package com.pizza.pizzaria.dtos.response;

import com.pizza.pizzaria.entities.enums.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderResponse(
        Long id,
        String name,
        Integer tableNumber,
        boolean draft,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
