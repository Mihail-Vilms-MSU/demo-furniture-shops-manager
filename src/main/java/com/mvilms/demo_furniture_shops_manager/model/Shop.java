package com.mvilms.demo_furniture_shops_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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

    @OneToMany
    @JoinColumn(name = "shop_id") // we need to duplicate the physical information
    private Set<Employee> employees;

    public Shop(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
