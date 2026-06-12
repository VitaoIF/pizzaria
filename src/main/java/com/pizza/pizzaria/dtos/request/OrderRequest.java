package com.pizza.pizzaria.dtos.request;

import com.pizza.pizzaria.entities.enums.OrderStatus;

public record OrderRequest(
        String name,
        Integer tableNumber,
        boolean draft,
        OrderStatus status
) {
}
