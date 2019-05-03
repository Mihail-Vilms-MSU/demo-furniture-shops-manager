package com.mvilms.demo_furniture_shops_manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "shop")
@Embeddable
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String state;
    private String address;
    private String phone;

    private Date createdAt;
    private Date updatedAt;

    public Shop(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
