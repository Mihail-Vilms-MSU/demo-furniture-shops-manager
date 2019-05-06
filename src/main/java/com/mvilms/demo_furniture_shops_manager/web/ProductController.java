package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.exceptions.ProductNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.resources.ProductResource;
import com.mvilms.demo_furniture_shops_manager.resources.ProductResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ProductController {
    private final ProductService service;
    private final ProductResourceAssembler assembler;
    public ProductController(ProductService service, ProductResourceAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    //////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products/{id}")
    public ProductResource getById(@PathVariable Long id) {
        return assembler.toResource(service.getById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products")
    public PagedResources<ProductResource> getAll(Pageable pageable) {
        Page page = service.getAll(pageable);

        List<ProductResource> productResourceList = (List) page.getContent()
            .stream()
            .map(product -> {
                return assembler.toResource((Product) product);
            })
            .collect(Collectors.toList());

        PagedResources.PageMetadata pageMetadata =
                new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());

        return new PagedResources<ProductResource>(productResourceList, pageMetadata);
    }

    //////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/products")
    ProductResource addNew(@RequestBody Product newProduct) {
        return assembler.toResource(service.save(newProduct));
    }

    @PutMapping("/products/{id}")
    ProductResource update(@RequestBody Product newProduct, @PathVariable Long id) throws URISyntaxException {
        Product savedProduct;

        // java 8 optional - isPresent()

        try {
            Product oldProduct = service.getById(id);

            if (newProduct.getName() != null)
                oldProduct.setName(newProduct.getName());
            if (newProduct.getDescription() != null)
                oldProduct.setDescription(newProduct.getDescription());
            if (newProduct.getPrice() != null)
                oldProduct.setPrice(newProduct.getPrice());
            if (newProduct.getType() != null)
                oldProduct.setType(newProduct.getType());

            savedProduct = service.save(oldProduct);
        } catch (ProductNotFoundException exception){ // haven't found existing product record
        savedProduct = service.save(newProduct);
    }

        return assembler.toResource(savedProduct);
    }

    @DeleteMapping("/products/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
