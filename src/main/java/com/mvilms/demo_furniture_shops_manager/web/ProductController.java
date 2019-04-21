package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.exceptions.ProductNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.resources.ProductResource;
import com.mvilms.demo_furniture_shops_manager.resources.ProductResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductResourceAssembler assembler;

    // #TODO#
    // Implement "NOT_FOUND" Exception
    @GetMapping("/products/{id}")
    ProductResource getById(@PathVariable Long id) {
        return assembler.toResource(productService.getById(id));
    }

    @GetMapping("/products")
    Resources<ProductResource> getAll() {

        List<ProductResource> productResourcesList =
                assembler.toResources(productService.getAll());

        Resources<ProductResource> productResources =
                new Resources<ProductResource>(productResourcesList);

        productResources
                .add(linkTo(methodOn(ProductController.class).getAll()).withSelfRel());

        return productResources;
    }

    @PostMapping("/products")
    ProductResource addNew(@RequestBody Product newProduct) {
        return assembler.toResource(productService.save(newProduct));
    }

    @PutMapping("/products/{id}")
    ProductResource update(@RequestBody Product newProduct, @PathVariable Long id) throws URISyntaxException {
        Product savedProduct;

        try {
            Product oldProduct = productService.getById(id);

            if (newProduct.getName() != null)
                oldProduct.setName(newProduct.getName());
            if (newProduct.getDescription() != null)
                oldProduct.setDescription(newProduct.getDescription());
            if (newProduct.getPrice() != null)
                oldProduct.setPrice(newProduct.getPrice());
            if (newProduct.getType() != null)
                oldProduct.setType(newProduct.getType());

            savedProduct = productService.save(oldProduct);
        } catch (ProductNotFoundException exception){ // haven't found existing product record
            savedProduct = productService.save(newProduct);
        }

        return assembler.toResource(savedProduct);
    }

    @DeleteMapping("/products/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
