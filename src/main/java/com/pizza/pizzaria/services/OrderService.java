package com.pizza.pizzaria.services;

import com.pizza.pizzaria.dtos.request.OrderRequest;
import com.pizza.pizzaria.dtos.response.OrderResponse;
import com.pizza.pizzaria.entities.Order;
import com.pizza.pizzaria.mapper.OrderMapper;
import com.pizza.pizzaria.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderResponse insert(OrderRequest orderRequest){
        Order order = OrderMapper.toEntity(orderRequest);

        Order save = orderRepository.save(order);

        return OrderMapper.toOrderResponse(save);
    }
}
