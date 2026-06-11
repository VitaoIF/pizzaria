package com.pizza.pizzaria.controller;

import com.pizza.pizzaria.dtos.request.CategoryRequest;
import com.pizza.pizzaria.dtos.response.CategoryResponse;
import com.pizza.pizzaria.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.insert(categoryRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(categoryResponse);
    }
}
