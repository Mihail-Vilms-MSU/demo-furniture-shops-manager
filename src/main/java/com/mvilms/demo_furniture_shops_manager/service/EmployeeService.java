package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.EmployeeRepository;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    /**
     * Retrieves one entry from database by its id
     *
     * @param id Id of record method shall return
     * @return Employee record
     */
    public Employee getById(String id) {
        return repository.getOne(id);
    }


    /**
     * Retrieves all Employee records from database
     *
     * @param pageable Pagination configuration
     * @return Page of Employee records
     */
    public Page<Employee> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }


    /**
     * Retrieves all records that contains input value in their fields
     *
     * @param searchInput Value to search through all records
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    public Page<Employee> liveSearch(String searchInput, Pageable pageable){
        return repository.liveSearch(searchInput, pageable);
    }

    /**
     * Retrieves all records that meet search criteria
     *
     * @param firstName Search parameter for "first.name" column
     * @param lastName Search parameter for "last.name" column
     * @param shopId Search parameter for "shop.id" column
     * @param role Search parameter for "role" column
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    public Page<Employee> advancedSearch(String firstName, String lastName, String shopId, String role, Pageable pageable){
        return repository.advancedSearch(firstName, lastName, shopId, role, pageable);
    }


    /**
     * Creates / Updates employee record
     *
     * @param newEmployee Object to create/update in database
     * @return Created/Updated record
     */
    public Employee save(Employee newEmployee){
        return repository.save(newEmployee);
    }


    /**
     * Returns all List of Employee records that linked to particular shop
     *
     * @param shopId Related shop records id
     * @return List of employee records
     */
    public List<Employee> getByShopId(String shopId){
        return repository.findByShopId(shopId);
    }


    /**
     * Deletes one record from database by id
     * @param id Id of record we need to delete
     */
    public void delete(String id) {
        repository.deleteById(id);
    }
}
