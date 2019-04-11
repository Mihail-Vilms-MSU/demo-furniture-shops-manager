package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    Resource<Product> getProductById(@PathVariable Long id) {
        log.info(" ~~~ ~~~ getProductById() has been invoked");
        Product product = productService.getProductById(id);

        return new Resource<>(product,
                linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));
    }

    @GetMapping("/products")
    Resources<Resource<Product>> getAllProducts() {
        log.info(" ~~~ ~~~ getAllProducts() has been invoked");
        List<Product> products = productService.getAllProducts();

        List<Resource<Product>> productResources = products.stream()
            .map(product -> new Resource<>(product,
                linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products")))
            .collect(Collectors.toList());

        return new Resources<>(productResources,
                linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }
}
