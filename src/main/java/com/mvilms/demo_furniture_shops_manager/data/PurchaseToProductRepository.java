package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.PurchaseToProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseToProductRepository extends JpaRepository<PurchaseToProduct, Long> {
}
