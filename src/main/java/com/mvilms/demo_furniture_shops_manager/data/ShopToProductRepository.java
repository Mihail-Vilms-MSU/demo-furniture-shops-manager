package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import com.mvilms.demo_furniture_shops_manager.resources.AmountResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "productReserve", path = "productReserves")
public interface ShopToProductRepository extends JpaRepository<ShopToProduct, Long> {

    @Query("SELECT p FROM ShopToProduct p")
    Page<ShopToProduct> findAll(Pageable p);

    @Query("SELECT p FROM ShopToProduct p WHERE p.shop.id = :shopId")
    List<ShopToProduct> findByShopId(@Param("shopId") String shopId);

    @Query("SELECT p FROM ShopToProduct p WHERE p.product.id = :productId")
    List<ShopToProduct> findByProductId(@Param("productId") String productId);

    @Query("SELECT p FROM ShopToProduct p WHERE p.shop.id = :shopId AND p.product.id = :productId")
    ShopToProduct findOneRecord(@Param("shopId") String shopId, @Param("productId") String productId);
}
