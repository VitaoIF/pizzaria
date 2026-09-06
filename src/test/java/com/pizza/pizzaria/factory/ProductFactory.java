package com.pizza.pizzaria.factory;

import com.pizza.pizzaria.entities.Category;
import com.pizza.pizzaria.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ProductFactory {
    private static final LocalDateTime createdAt = LocalDateTime.of(2026, Month.SEPTEMBER, 5, 16, 45);
    private static final LocalDateTime updatedAt = LocalDateTime.of(2026, Month.SEPTEMBER, 6, 16, 45);
    private static final Category category = new Category(1L, "Pizzas");


    public static Product criar(){
        return Product.builder()
                .id(1L)
                .name("Pizza de calabresa")
                .description("Uma pizza de calabresa")
                .banner("img.jpg")
                .price(new BigDecimal("71.00"))
                .active(true)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .category(category)
                .build();
    }

    public static List<Product> criarLista(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(2L, "Pizza de Queijo", "Uma pizza de queijo", "img.jpg", new BigDecimal("80.00"), true, createdAt, updatedAt, category));
        products.add(new Product(3L, "Pizza de Frango com Catupyri", "Pizza de Frango com Catupiry", "img.jpg", new BigDecimal("120.00"), true, createdAt, updatedAt, category));
        products.add(new Product(4L, "Pizza 4 queijos", "Uma pizza com quatro queijos", "img.jpg", new BigDecimal("100.00"), true, createdAt, updatedAt, category));
        return products;
    }
}
