package com.pizza.pizzaria.controller;

import com.pizza.pizzaria.dtos.request.CategoryRequest;
import com.pizza.pizzaria.dtos.response.CategoryResponse;
import com.pizza.pizzaria.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        CategoryResponse categoryResponse = categoryService.findById(id);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> findAll(Pageable pageable){
        Page<CategoryResponse> categoryResponse = categoryService.findAll(pageable);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.update(id, categoryRequest);
        return ResponseEntity.ok().body(categoryResponse);
    }
}
