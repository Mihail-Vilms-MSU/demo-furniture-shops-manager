package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("SELECT e FROM Employee e")
    Page<Employee> findAll(Pageable p);

    @Query("SELECT e FROM Employee e WHERE e.role LIKE %:role%")
    Page<Employee> findByRole(@Param("role") String role, Pageable p);

    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:email%")
    Page<Employee> findByEmail(@Param("email") String email, Pageable p);

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:name% OR e.lastName LIKE %:name%")
    Page<Employee> findByName(@Param("name") String name, Pageable p);

    @Query("SELECT e FROM Employee e WHERE e.shop.id = :shopId")
    List<Employee> findByShopId(@Param("shopId") String shopId);

    @Query("SELECT e from Employee e WHERE" +
            " e.id LIKE %:searchInput% OR" +
            " e.firstName LIKE %:searchInput% OR" +
            " e.lastName LIKE %:searchInput% OR" +
            " e.role LIKE %:searchInput% OR" +
            " e.email LIKE %:searchInput%"
    )
    Page<Employee> liveSearch(@Param("searchInput") String searchInput, Pageable p);

    @Query("SELECT e from Employee e WHERE" +
            " (:firstName='' or e.firstName LIKE %:firstName%) AND" +
            " (:lastName='' or e.lastName LIKE %:lastName%) AND" +
            " (:shopId='' or e.shop.id LIKE %:shopId%) AND" +
            " (:role='' or e.role LIKE %:role%)"
    )
    Page<Employee> advancedSearch(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("shopId") String shopId,
            @Param("role") String role,
            Pageable p
    );
}
