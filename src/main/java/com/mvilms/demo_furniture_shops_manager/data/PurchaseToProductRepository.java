package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.PurchaseToProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "purchasePosition", path = "purchasePositions")
public interface PurchaseToProductRepository extends JpaRepository<PurchaseToProduct, Long> {

    @Query("SELECT p FROM PurchaseToProduct p WHERE p.purchase.id = :purchaseId")
    List<PurchaseToProduct> getProductsInPurchase(@Param("purchaseId") String purchaseId);

}
