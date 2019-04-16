package com.mvilms.demo_furniture_shops_manager.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal type;
    private String description;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    public Product(){
        this.isActive = true;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Product(String name, BigDecimal price){
        this();
        this.name = name;
        this.price = price;
    }

    public Product(String name, BigDecimal price, String description){
        this(name, price);
        this.description = description;
    }
}
