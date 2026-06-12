package com.pizza.pizzaria.mapper;

import com.pizza.pizzaria.dtos.request.OrderRequest;
import com.pizza.pizzaria.dtos.response.OrderResponse;
import com.pizza.pizzaria.entities.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMapper {

    public static Order toEntity(OrderRequest orderRequest){
        return Order.builder()
                .name(orderRequest.name())
                .tableNumber(orderRequest.tableNumber())
                .draft(orderRequest.draft())
                .status(orderRequest.status())
                .build();
    }

    public static OrderResponse toOrderResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .name(order.getName())
                .tableNumber(order.getTableNumber())
                .draft(order.isDraft())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
