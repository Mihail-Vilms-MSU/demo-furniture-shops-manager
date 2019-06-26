package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "shops", path = "shops")
public interface ShopRepository extends JpaRepository<Shop, String> {

    @Query("SELECT s FROM Shop s")
    Page<Shop> findAll(Pageable p);

    @Query("SELECT s from Shop s WHERE s.name LIKE %:name%")
    Page<Shop> findByName(@Param("name") String name, Pageable p);

    @Query("SELECT s from Shop s WHERE s.state LIKE %:state%")
    Page<Shop> findByState(@Param("state") String state, Pageable p);

    @Query("SELECT s from Shop s WHERE s.city LIKE %:city%")
    Page<Shop> findByCity(@Param("city") String city, Pageable p);

    @Query("SELECT s from Shop s WHERE s.address LIKE %:address%")
    Page<Shop> findByAddress(@Param("address") String address, Pageable p);


    @Query("SELECT DISTINCT s.state from Shop s")
    List<String> getListOfStates();

    @Query("SELECT DISTINCT s.city from Shop s WHERE (:state='' or s.state=:state)")
    List<String> getListOfCities(@Param("state") String state);


    @Query("SELECT s from Shop s WHERE" +
        " s.id LIKE %:searchInput% OR" +
        " s.name LIKE %:searchInput% OR" +
        " s.state LIKE %:searchInput% OR" +
        " s.city LIKE %:searchInput% OR " +
        " s.phone LIKE %:searchInput%"
    )
    Page<Shop> findByAllFields(@Param("searchInput") String searchInput, Pageable p);

    @Query("SELECT s from Shop s WHERE" +
            " (:name='' or s.name LIKE %:name%) and" +
            " (:state='' or s.state LIKE %:state%) and " +
            " (:city='' or s.city LIKE %:city%)"
    )
    Page<Shop> advancedSearch(
        @Param("name") String name,
        @Param("state") String state,
        @Param("city") String city,
        Pageable page
    );
}
