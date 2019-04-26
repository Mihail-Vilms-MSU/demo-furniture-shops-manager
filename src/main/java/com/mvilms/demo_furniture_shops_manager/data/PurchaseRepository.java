package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("SELECT p FROM Purchase p")
    Page<Purchase> findAll(Pageable p);

    @Query("SELECT p FROM Purchase p WHERE p.employeeId = :employeeId")
    Page<Purchase> findByEmployeeId(@Param("employeeId") Long employeeId, Pageable p);


    // Search by price;

    @Query("SELECT p FROM Purchase p WHERE p.totalPrice >= :price")
    Page<Purchase> findWithPriceAbove(@Param("price") BigDecimal price, Pageable p);

    @Query("SELECT p FROM Purchase p WHERE p.totalPrice <= :price")
    Page<Purchase> findWithPriceBelow(@Param("price") BigDecimal price, Pageable p);

    @Query("SELECT p FROM Purchase p WHERE p.totalPrice >= :bottom AND p.totalPrice <= :top")
    Page<Purchase> findWithPriceBetween(@Param("bottom") BigDecimal bottom,
                                       @Param("top") BigDecimal top,
                                       Pageable p);

    // Search by createdAt;
    /*
    @Query("SELECT p FROM Purchase p WHERE p.createdAt <= :createdAt")
    Page<Purchase> findBefore(@Param("price") BigDecimal price, Pageable p);

    @Query("SELECT p FROM Purchase p WHERE p.price >= :bottom AND p.price <= :top")
    Page<Purchase> findAfter(@Param("bottom") BigDecimal bottom,
                                        @Param("top") BigDecimal top,
                                        Pageable p);
    */
}
