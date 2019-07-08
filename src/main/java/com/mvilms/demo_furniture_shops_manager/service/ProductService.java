package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ProductRepository;
import com.mvilms.demo_furniture_shops_manager.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;


    /**
     * Retrieves one entry from database by its id
     *
     * @param id Id of record method shall return
     * @return Product record
     */
    public Product getById(String id){
        return repository.getOne(id);
    }


    /**
     * Retrieves all Product records from database
     *
     * @param pageable Pagination configuration
     * @return Page of Product records
     */
    public Page<Product> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }


    /**
     * Retrieves all records that contains input value in their fields
     *
     * @param searchInput Value to search through all records
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    public Page<Product> liveSearch(String searchInput, Pageable pageable){
        return repository.liveSearch(searchInput, pageable);
    }


    /**
     * Retrieves all records that meet search criteria
     *
     * @param name Search parameter for "name" column
     * @param type Search parameter for "type" column
     * @param minPrice Low boundary for "price" column
     * @param maxPrice Top boundary for "price" column
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    public Page<Product> advancedSearch(String name, String type, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable){
        return repository.advancedSearch(name, type, minPrice, maxPrice, pageable);
    }


    /**
     * Creates / Updates product record
     *
     * @param newProduct Object to create/update in data base
     * @return Created/Updated record
     */
    public Product save(Product newProduct){
        return repository.save(newProduct);
    }


    /**
     * Deletes one record from database by id
     * @param id Id of record we need to delete
     */
    public void delete(String id) {
        repository.deleteById(id);
    }
}
