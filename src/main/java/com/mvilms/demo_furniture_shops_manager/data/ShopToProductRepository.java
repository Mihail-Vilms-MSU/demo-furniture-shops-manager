package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopToProductRepository extends JpaRepository<ShopToProduct, Long> {

    // Returns all records from "shop_to_product" table
    @Query("SELECT p FROM ShopToProduct p WHERE p.shopId = :shopId")
    List<ShopToProduct> findAll(@Param("shopId") Long shopId);

    // Returns record from "shop_to_product" table
    @Query("SELECT p FROM ShopToProduct p WHERE p.shopId = :shopId AND p.productId = :productId")
    ShopToProduct getRecord(@Param("shopId") Long shopId, @Param("productId") Long productId);

    // Returns value - amount of product in shop
    @Query("SELECT p.amount FROM ShopToProduct p WHERE p.shopId = :shopId AND p.productId = :productId")
    Long getAmount(@Param("shopId") Long shopId, @Param("productId") Long productId);

}
