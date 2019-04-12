package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ProductResourceAssembler productResourceAssembler;

    ProductController(ProductService productService,
                       ProductResourceAssembler productResourceAssembler) {

        this.productService = productService;
        this.productResourceAssembler = productResourceAssembler;
    }

    @GetMapping("/products/{id}")
    Resource<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productResourceAssembler.toResource(product);
    }

    @GetMapping("/products")
    Resources<Resource<Product>> getAllProducts() {
        List<Product> products = productService.getAll();

        List<Resource<Product>> productResources = products.stream()
                .map(productResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(productResources,
                linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }

    @PostMapping("/products")
    ResponseEntity<?> newProduct(@RequestBody Product newProduct) throws URISyntaxException {
        Resource<Product> resource = productResourceAssembler
                .toResource(productService.save(newProduct));
        return ResponseEntity
            .created(new URI(resource.getId().expand().getHref()))
            .body(resource);
    }

    @PutMapping("/products/{id}")
    ResponseEntity<?> updateProduct(@RequestBody Product newProduct, @PathVariable Long id)
            throws URISyntaxException {

        Product oldProduct = productService.getById(id);
        Product savedProduct;
        if (oldProduct == null){
            savedProduct = productService.save(newProduct);
        } else {    // what to do if there will be many fields(????)
            oldProduct.setName(newProduct.getName());
            oldProduct.setDescription(newProduct.getDescription());
            oldProduct.setPrice(newProduct.getPrice());
            savedProduct = productService.save(newProduct);
        }

        Resource<Product> resourceProduct = productResourceAssembler.toResource(savedProduct);

        return ResponseEntity
                .created(new URI(resourceProduct.getId().expand().getHref()))
                .body(resourceProduct);
    }

    @DeleteMapping("/product/{id}")
    void deleteEmployee(@PathVariable Long id) {
        productService.delete(id);
    }

}
