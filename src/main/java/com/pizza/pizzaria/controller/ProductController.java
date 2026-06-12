package com.pizza.pizzaria.controller;

import com.pizza.pizzaria.dtos.request.ProductRequest;
import com.pizza.pizzaria.dtos.response.ProductResponse;
import com.pizza.pizzaria.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable){
        Page<ProductResponse> productResponse = productService.findAll(pageable);
        return ResponseEntity.ok().body(productResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        ProductResponse productResponse = productService.findById(id);
        return ResponseEntity.ok().body(productResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
