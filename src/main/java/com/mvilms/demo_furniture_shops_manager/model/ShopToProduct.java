package com.mvilms.demo_furniture_shops_manager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shop_to_product")
public class ShopToProduct{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Long amount;

    public ShopToProduct(){}

    public ShopToProduct(Shop shop, Product product, Long amount){
        this();
        this.shop = shop;
        this.product = product;
        this.amount = amount;
    }

}
