package com.mvilms.demo_furniture_shops_manager.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "product")
@Embeddable
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private BigDecimal price;
    private String type;
    private String description;

    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    public Product(){};

    public Product(Boolean isActive){
        this();
        this.isActive = isActive;
    }

    public Product(String name, BigDecimal price, String type){
        this(true);
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Product(String name, BigDecimal price, String type, Boolean isActive){
        this(isActive);
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Product(String name, BigDecimal price, String type, String description){
        this(name, price, type);
        this.description = description;
    }
}
