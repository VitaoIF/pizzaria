package com.pizza.pizzaria.services;

import com.pizza.pizzaria.dtos.request.ProductRequest;
import com.pizza.pizzaria.dtos.response.ProductResponse;
import com.pizza.pizzaria.entities.Category;
import com.pizza.pizzaria.entities.Product;
import com.pizza.pizzaria.mapper.ProductMapper;
import com.pizza.pizzaria.repository.CategoryRepository;
import com.pizza.pizzaria.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    public Page<ProductResponse> findAll(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductMapper::toProductResponse);
    }

    @Transactional
    public ProductResponse findById(Long id){
        Product productById = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return ProductMapper.toProductResponse(productById);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest productRequest){
        Product product = productRepository.getReferenceById(id);
        Category category = categoryRepository.getReferenceById(productRequest.categoryId());
        updateProduct(product, productRequest, category);

        Product updated = productRepository.save(product);

        return ProductMapper.toProductResponse(updated);
    }

    private void updateProduct(Product product, ProductRequest productRequest, Category category) {
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setBanner(productRequest.banner());
        product.setActive(productRequest.active());
        product.setCategory(category);
    }
}
