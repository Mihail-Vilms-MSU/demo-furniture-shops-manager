package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopToProductRepository extends JpaRepository<ShopToProduct, Long> {
}
