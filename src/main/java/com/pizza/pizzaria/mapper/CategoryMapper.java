package com.pizza.pizzaria.mapper;

import com.pizza.pizzaria.dtos.request.CategoryRequest;
import com.pizza.pizzaria.dtos.response.CategoryResponse;
import com.pizza.pizzaria.entities.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toEntity(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
