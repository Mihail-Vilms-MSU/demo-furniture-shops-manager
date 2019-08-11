package com.mvilms.demo_furniture_shops_manager.model;

import com.mvilms.demo_furniture_shops_manager.data.EmployeeRepository;
import com.mvilms.demo_furniture_shops_manager.service.ShopService;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

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

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public Employee() {}

    public Employee(String firstName, String lastName, String role, String phone, String email, Shop shop){
        this();

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phone = phone;
        this.email = email;

        this.shop = shop;
    }
}
