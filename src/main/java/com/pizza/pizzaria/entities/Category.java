package com.pizza.pizzaria.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", allocationSize = 50)
    private Long id;

    private String name;
}
