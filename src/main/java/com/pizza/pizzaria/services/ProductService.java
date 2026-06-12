package com.pizza.pizzaria.services;

import com.pizza.pizzaria.dtos.request.ProductRequest;
import com.pizza.pizzaria.dtos.response.ProductResponse;
import com.pizza.pizzaria.entities.Category;
import com.pizza.pizzaria.entities.Product;
import com.pizza.pizzaria.mapper.ProductMapper;
import com.pizza.pizzaria.repository.CategoryRepository;
import com.pizza.pizzaria.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public ProductResponse insert(ProductRequest productRequest){
        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Product product = ProductMapper.toEntity(productRequest, category);
        Product save = productRepository.save(product);

        return ProductMapper.toProductResponse(save);
    }
}
