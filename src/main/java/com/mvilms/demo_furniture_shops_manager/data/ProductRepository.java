package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Product;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

/**
 * http://localhost:8080/products/search/findAll?page=2&size=3&sort=price,asc
 * http://localhost:8080/products/search/findByName?name=Chair
 * http://localhost:8080/products/search/findWithPriceBetween?bottom=200&top=700&sort=price, desc
 */
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p")
    Page<Product> findAll(Pageable p);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    Page<Product> findByName(@Param("name") String name, Pageable p);

    @Query("SELECT p FROM Product p WHERE p.type LIKE %:type%")
    Page<Product> findByType(@Param("type") String type, Pageable p);

    @Query("SELECT p FROM Product p WHERE p.isActive = :active")
    Page<Product> findByActive(@Param("active") Boolean active, Pageable p);

    @Query("SELECT p FROM Product p WHERE p.price >= :bottom AND p.price <= :top")
    Page<Product> findByPrice(@Param("bottom") BigDecimal bottom,
                              @Param("top") BigDecimal top,
                              Pageable p);

    @Query("SELECT MAX(price) FROM Product")
    BigDecimal findMaxPrice();

    @Query("SELECT MIN(price) FROM Product p")
    BigDecimal findMinPrice();


    @Query("SELECT p from Product p WHERE" +
            " p.id LIKE %:searchInput% OR" +
            " p.name LIKE %:searchInput% OR" +
            " p.type LIKE %:searchInput% OR" +
            " p.description LIKE %:searchInput%"
    )
    Page<Product> findByAllFields(@Param("searchInput") String searchInput, Pageable p);

}
