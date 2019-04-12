package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ProductResourceAssembler implements ResourceAssembler<Product, Resource<Product>> {
    @Override
    public Resource<Product> toResource(Product product) {
       return new Resource<>(product,
        linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel(),
        linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));
    }
}
