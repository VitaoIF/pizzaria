package com.pizza.pizzaria.service;

import com.pizza.pizzaria.dtos.request.ProductRequest;
import com.pizza.pizzaria.dtos.response.ProductResponse;
import com.pizza.pizzaria.entities.Product;
import com.pizza.pizzaria.exceptions.custom.CategoryNotFoundException;
import com.pizza.pizzaria.exceptions.custom.ProductNotFoundException;
import com.pizza.pizzaria.factory.ProductFactory;
import com.pizza.pizzaria.repository.CategoryRepository;
import com.pizza.pizzaria.repository.ProductRepository;
import com.pizza.pizzaria.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Pageable pageable;
    private Page<Product> products;
    private Page<Product> empty;
    private Product product;

    @BeforeEach
    void setUp(){
        product = ProductFactory.criar();
        List<Product> productsList = ProductFactory.criarLista();
        List<Product> emptyProducts = new ArrayList<>();
        pageable = PageRequest.of(0, 10);
        products = new PageImpl<>(productsList, pageable, 3);
        empty = new PageImpl<>(emptyProducts, pageable, 0);
    }

    @Test
    void shouldReturnProductsPageWhenProductsExist() {
        when(productRepository.findAll(pageable)).thenReturn(products);
        Page<ProductResponse> resultado = productService.findAll(pageable);
        assertEquals(3, resultado.getTotalElements());
        assertEquals("Pizza de Queijo", resultado.getContent().get(0).name());

        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void shouldReturnEmptyListWhenProductsDontExist(){
        when(productRepository.findAll(pageable)).thenReturn(empty);
        Page<ProductResponse> responses = productService.findAll(pageable);
        assertEquals(0 ,responses.getTotalElements());
        assertTrue(responses.getContent().isEmpty());

        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void shouldReturnOneProductWhenIdExists(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductResponse productResponse = productService.findById(1L);
        assertEquals("Pizza de calabresa", productResponse.name());

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void shouldReturnProductNotFoundExceptionWhenProductIdDontExists(){
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> {
            productService.findById(2L);
        });

        verify(productRepository, times(1)).findById(2L);
    }

    @Test
    void shouldInsertAProductWhenCategoryExists(){
        ProductRequest productRequest = new ProductRequest(
                "Pizza de calabresa",
                "Uma pizza de calabresa",
                "img.jpg",
                new BigDecimal("71.00"),
                true,
                1L
        );

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(product.getCategory()));

        when(productRepository.save(any(Product.class)))
                .thenReturn(product);

        ProductResponse productResponse = productService.insert(productRequest);

        assertEquals("Pizza de calabresa", productResponse.name());

        verify(categoryRepository).findById(1L);
        verify(productRepository).save(productArgumentCaptor.capture());

        assertEquals(product.getName(), productArgumentCaptor.getValue().getName());
    }

    @Test
    void shouldNotInsertAProductWhenCategoryNotExists(){
        ProductRequest productRequest = new ProductRequest(
                "Pizza de calabresa",
                "Uma pizza de calabresa",
                "img.jpg",
                new BigDecimal("71.00"),
                true,
                1L
        );
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            productService.insert(productRequest);
        });

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void shouldUpdateProductWhenIdExists(){
        ProductRequest productRequest = new ProductRequest(
                "Pizza de Queijo",
                "Uma pizza de Queijo",
                "img.jpg",
                new BigDecimal("71.00"),
                true,
                1L
        );

        when(productRepository.getReferenceById(1L)).thenReturn(product);
        when(categoryRepository.getReferenceById(productRequest.categoryId())).thenReturn(product.getCategory());
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse productResponse = productService.update(1L, productRequest);

        assertEquals("Pizza de Queijo", productResponse.name());

        verify(productRepository, times(1)).getReferenceById(1L);
        verify(categoryRepository, times(1)).getReferenceById(productRequest.categoryId());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void shouldNotUpdateWhenIdDoesNotExist(){
        ProductRequest productRequest = new ProductRequest(
                "Pizza de calabresa",
                "Uma pizza de calabresa",
                "img.jpg",
                new BigDecimal("71.00"),
                true,
                1L
        );
        when(productRepository.getReferenceById(1L)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> {
           productService.update(1L, productRequest);
        });

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void shouldDeleteProductWhenIdExists(){
        productService.delete(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist(){
        doThrow(ProductNotFoundException.class).when(productRepository).deleteById(2L);

        assertThrows(ProductNotFoundException.class, () -> {
            productService.delete(2L);
        });

        verify(productRepository, times(1)).deleteById(2L);
    }
}
