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

    public Product getById(String id){
        return repository.getOne(id);
    }

    public Page getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page findByName(String name, Pageable pageable) {
        return repository.findByName(name, pageable);
    }

    public Page findByType(String type, Pageable pageable) {
        return repository.findByType(type, pageable);
    }

    public Page findByActive(Boolean isActive, Pageable pageable) {
        return repository.findByActive(isActive, pageable);
    }

    public Page findByPrice(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return repository.findByPrice(minPrice, maxPrice, pageable);
    }

    public Product save(Product newProduct){
        return repository.save(newProduct);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
