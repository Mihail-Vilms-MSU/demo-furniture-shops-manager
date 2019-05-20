package com.mvilms.demo_furniture_shops_manager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase_to_product")
public class PurchaseToProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Long amount;

    public PurchaseToProduct(){
    }

    public PurchaseToProduct(Purchase purchase, Product product, Long amount){
        this();
        this.purchase = purchase;
        this.product = product;
        this.amount = amount;
    }
}
