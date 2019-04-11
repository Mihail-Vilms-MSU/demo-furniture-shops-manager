package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ProductRepository;
import com.mvilms.demo_furniture_shops_manager.exceptions.ProductNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product getProductById(Long id) throws ProductNotFoundException{
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
