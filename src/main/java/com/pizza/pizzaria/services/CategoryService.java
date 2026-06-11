package com.pizza.pizzaria.services;

import com.pizza.pizzaria.dtos.request.CategoryRequest;
import com.pizza.pizzaria.dtos.response.CategoryResponse;
import com.pizza.pizzaria.entities.Category;
import com.pizza.pizzaria.mapper.CategoryMapper;
import com.pizza.pizzaria.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponse insert(CategoryRequest categoryRequest){
        Category category = CategoryMapper.toEntity(categoryRequest);

        Category saved = categoryRepository.save(category);

        return CategoryMapper.toCategoryResponse(saved);
    }
}
