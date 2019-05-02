package com.mvilms.demo_furniture_shops_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shopId;
    private String role;
}
