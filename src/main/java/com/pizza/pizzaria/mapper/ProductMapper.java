package com.pizza.pizzaria.mapper;

import com.pizza.pizzaria.dtos.request.ProductRequest;
import com.pizza.pizzaria.dtos.response.ProductResponse;
import com.pizza.pizzaria.entities.Category;
import com.pizza.pizzaria.entities.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public static Product toEntity(ProductRequest productRequest, Category category){
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .banner(productRequest.banner())
                .price(productRequest.price())
                .active(productRequest.active())
                .category(category)
                .build();
    }

    public static ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .banner(product.getBanner())
                .price(product.getPrice())
                .active(product.isActive())
                .categoryId(product.getId())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
