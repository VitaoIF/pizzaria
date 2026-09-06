package com.pizza.pizzaria.service;

import com.pizza.pizzaria.dtos.response.ProductResponse;
import com.pizza.pizzaria.entities.Product;
import com.pizza.pizzaria.factory.ProductFactory;
import com.pizza.pizzaria.repository.ProductRepository;
import com.pizza.pizzaria.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Pageable pageable;
    private Page<Product> products;

    @BeforeEach
    void setUp(){
        Product product = ProductFactory.criar();
        List<Product> productsList = ProductFactory.criarLista();
        pageable = PageRequest.of(0, 10);
        products = new PageImpl<>(productsList, pageable, 3);
    }

    @Test
    void shouldReturnProductsPageWhenProductsExist() {
        when(productRepository.findAll(pageable)).thenReturn(products);
        Page<ProductResponse> resultado = productService.findAll(pageable);
        assertEquals(3, resultado.getTotalElements());
        assertEquals("Pizza de Queijo", resultado.getContent().get(0).name());
    }


}
