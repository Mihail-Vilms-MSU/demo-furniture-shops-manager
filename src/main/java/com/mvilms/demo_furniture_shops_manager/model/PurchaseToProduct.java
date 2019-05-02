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
    private Long purchaseId;
    private Long productId;
    private Long amount;

    public PurchaseToProduct(){
    }

    public PurchaseToProduct(Long purchaseId, Long productId, Long amount){
        this();
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.amount = amount;
    }
}
