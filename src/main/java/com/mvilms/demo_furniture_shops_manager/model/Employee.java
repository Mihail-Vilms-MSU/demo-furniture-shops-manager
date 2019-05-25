package com.mvilms.demo_furniture_shops_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    private String role;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
