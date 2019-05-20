package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ProductRepository;
import com.mvilms.demo_furniture_shops_manager.exceptions.ProductNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) { this.repository = repository; }

    //////////////////////////////////////////////////////////////////////

    public Product getById(String id){ return repository.getOne(id); }

    public Page getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    //////////////////////////////////////////////////////////////////////

    public Product save(Product newProduct){
        return repository.save(newProduct);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
