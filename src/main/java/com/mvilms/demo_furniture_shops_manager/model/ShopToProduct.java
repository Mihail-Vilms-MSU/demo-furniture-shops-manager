package com.mvilms.demo_furniture_shops_manager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shop_to_product")
public class ShopToProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long shopId;
    private Long productId;
    private Long amount;

    public ShopToProduct(Long shopId, Long productId, Long amount){
        this.shopId = shopId;
        this.productId = productId;
        this.amount = amount;
    }
}
