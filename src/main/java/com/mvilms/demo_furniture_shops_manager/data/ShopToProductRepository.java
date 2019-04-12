package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopToProductRepository extends JpaRepository<ShopToProduct, Long> {
    @Query("SELECT p FROM ShopToProduct p WHERE p.shopId = :shopId")
    List<ShopToProduct> findAllProductsForShop(@Param("shopId") Long shopId);

    @Query("SELECT p FROM ShopToProduct p WHERE p.shopId = :shopId AND p.productId = :productId")
    ShopToProduct getProductAmount(@Param("shopId") Long shopId, @Param("productId") Long productId);

}
