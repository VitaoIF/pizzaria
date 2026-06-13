package com.pizza.pizzaria.controller;

import com.pizza.pizzaria.dtos.request.OrderRequest;
import com.pizza.pizzaria.dtos.response.OrderResponse;
import com.pizza.pizzaria.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> insert(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.insert(orderRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderResponse.id())
                .toUri();
        return ResponseEntity.created(uri).body(orderResponse);
    }

}
