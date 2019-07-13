package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.resources.ProductResource;
import com.mvilms.demo_furniture_shops_manager.resources.ProductResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Slf4j
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductResourceAssembler assembler;


    /**
     * Returns product record
     *
     * @param id Product records Id
     * @return Product record
     */
    @GetMapping("/products/{id}")
    public ProductResource getById(@PathVariable String id) {
        return assembler.toResource(service.getById(id));
    }


    /**
     * Takes no arguments or one argument - string in order to search records by multiple fields
     * In first case method returns all records in database in table "products"
     * In second case method returns records that contains input value in one of theirs fields
     *
     * @param searchInput String to search
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    @GetMapping("/products")
    public PagedResources<ProductResource> getAll(
            @RequestParam(value ="searchInput", required = false, defaultValue = "") String searchInput,
            Pageable pageable
    ) {
        Page page;
        if(searchInput.equals("")){
            page = service.getAll(pageable);
        } else {
            page = service.liveSearch(searchInput, pageable);
        }

        return PagedResourcesBuilder.<Product, ProductResource>build(page, assembler);
    }


    /**
     * Returns search result by several fields
     *
     * @param name Search parameter for "name" column
     * @param type Search parameter for "type" column
     * @param strMinPrice Low boundary for "price" column
     * @param strMaxPrice Top boundary for "price" column
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    @GetMapping("/products/advancedSearch")
    public PagedResources<ProductResource> advancedSearch(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "minPrice", required = false, defaultValue = "") String strMinPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "") String strMaxPrice,
            Pageable pageable
    ) {
        BigDecimal minPrice = strMinPrice.equals("") ? new BigDecimal("0") : new BigDecimal(strMinPrice);
        BigDecimal maxPrice = strMaxPrice.equals("") ? new BigDecimal("10000") : new BigDecimal(strMaxPrice);

        Page page = service.advancedSearch(name, type, minPrice, maxPrice, pageable);

        return PagedResourcesBuilder.<Product, ProductResource>build(page, assembler);
    }


    /**
     * Creates new product record
     *
     * @param newProduct json in request body that consists all fields values of new record
     * @return Product record created after handling request
     */
    @PostMapping("/products")
    ProductResource addNew(@RequestBody Product newProduct) {
        return assembler.toResource(service.save(newProduct));
    }


    /**
     * Updates employee record that already exists it database
     *
     * @param newProduct json with fields values to update
     * @param id product records id we shall update
     * @return product record updated after handling request
     */
    @PutMapping("/products/{id}")
    ProductResource update(@RequestBody Product newProduct, @PathVariable String id) {
        Product oldProduct = service.getById(id);

        if (newProduct.getName() != null)
            oldProduct.setName(newProduct.getName());
        if (newProduct.getDescription() != null)
            oldProduct.setDescription(newProduct.getDescription());
        if (newProduct.getPrice() != null)
            oldProduct.setPrice(newProduct.getPrice());
        if (newProduct.getType() != null)
            oldProduct.setType(newProduct.getType());

        Product savedProduct = service.save(oldProduct);

        return assembler.toResource(savedProduct);
    }

    /**
     * Deletes one record from database by id
     * @param id Id of record we need to delete
     */
    @DeleteMapping("/products/{id}")
    ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
