package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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

    @Query("SELECT e FROM Employee e WHERE e.shop.id = :shop")
    Page<Employee> findByShopId(@Param("shop") String shopId, Pageable p);

}
