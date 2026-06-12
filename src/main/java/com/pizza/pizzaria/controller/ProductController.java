package com.pizza.pizzaria.controller;

import com.pizza.pizzaria.dtos.request.ProductRequest;
import com.pizza.pizzaria.dtos.response.ProductResponse;
import com.pizza.pizzaria.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> insert(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.insert(productRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(productResponse);
    }
}
