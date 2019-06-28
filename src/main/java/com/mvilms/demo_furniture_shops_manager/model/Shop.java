package com.mvilms.demo_furniture_shops_manager.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "shop")
@Embeddable
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String city;
    private String state;
    private String address;
    private String phone;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public Shop(){};

    public Shop(String name, String city, String state, String address, String phone){
        this();
        this.name = name;
        this.city = city;
        this.state = state;
        this.address = address;
        this.phone = phone;
    }
}
