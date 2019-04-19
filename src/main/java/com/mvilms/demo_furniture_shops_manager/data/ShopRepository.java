package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "shops", path = "shops")
public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("SELECT s FROM Shop s")
    Page<Shop> findAll(Pageable p);

    @Query("SELECT s from Shop s WHERE s.name LIKE %:name%")
    Page<Shop> findByName(@Param("name") String name, Pageable p);

    @Query("SELECT s from Shop s WHERE s.city LIKE %:city%")
    Page<Shop> findByCity(@Param("city") String city, Pageable p);

    @Query("SELECT s from Shop s WHERE s.state LIKE %:state%")
    Page<Shop> findByState(@Param("state") String state, Pageable p);

    @Query("SELECT s from Shop s WHERE s.address LIKE %:address%")
    Page<Shop> findByAddress(@Param("address") String address, Pageable p);

}
