package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;

// http://localhost:5001/products/search/findAll

@CrossOrigin
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

    @Query("SELECT p from Product p WHERE" +
            " p.id LIKE %:searchInput% OR" +
            " p.name LIKE %:searchInput% OR" +
            " p.type LIKE %:searchInput% OR" +
            " p.description LIKE %:searchInput%"
    )
    Page<Product> liveSearch(@Param("searchInput") String searchInput, Pageable p);

    @Query( "SELECT p from Product p WHERE" +
            " (:name='' or p.name LIKE %:name%) AND" +
            " (:type='' or p.type LIKE %:type%) AND" +
            " (p.price >= :minPrice) AND" +
            " (p.price <= :maxPrice)"
    )
    Page<Product> advancedSearch(
            @Param("name") String name,
            @Param("type") String type,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable p
    );

    @Query("SELECT MAX(p.price) FROM Product p")
    BigDecimal findMaxPrice();

    @Query("SELECT MIN(p.price) FROM Product p")
    BigDecimal findMinPrice();
}
