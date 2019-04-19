package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

/**
 * http://localhost:8080/products/search/findAll?page=2&size=3&sort=price,asc
 *
 * http://localhost:8080/products/search/findByName?name=Chair
 * http://localhost:8080/products/search/findByPrice?price=399
 *
 * http://localhost:8080/products/search/findWithPriceBetween?bottom=200&top=700&sort=price, desc
 *

 */
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p")
    Page<Product> findAll(Pageable p);

    /*
    @Query("SELECT p FROM Product p WHERE p.is_active = :active")
    Page<Product> findAllActive(@Param("active") Boolean active, Pageable p);
    */

    @Query("SELECT p FROM Product p WHERE p.type LIKE %:type%")
    Page<Product> findByType(@Param("type") String type, Pageable p);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    Page<Product> findByName(@Param("name") String name, Pageable p);

    Page<Product> findByPrice(@Param("price") BigDecimal price, Pageable p);

    @Query("SELECT p FROM Product p WHERE p.price >= :price")
    Page<Product> findWithPriceAbove(@Param("price") BigDecimal price, Pageable p);

    @Query("SELECT p FROM Product p WHERE p.price <= :price")
    Page<Product> findWithPriceBelow(@Param("price") BigDecimal price, Pageable p);

    @Query("SELECT p FROM Product p WHERE p.price >= :bottom AND p.price <= :top")
    Page<Product> findWithPriceBetween(@Param("bottom") BigDecimal bottom,
                                       @Param("top") BigDecimal top,
                                       Pageable p);
}
