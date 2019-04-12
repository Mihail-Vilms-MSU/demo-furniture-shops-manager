package com.mvilms.demo_furniture_shops_manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;

    private Date createdAt;
    private Date updatedAt;

    public Shop(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
