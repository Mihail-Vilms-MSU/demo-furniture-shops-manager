package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import com.mvilms.demo_furniture_shops_manager.resources.*;
import com.mvilms.demo_furniture_shops_manager.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
public class PurchaseController {
    @Autowired
    PurchaseResourceAssembler assembler;
    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/purchases/{id}")
    public PurchaseResource getById(@PathVariable Long id) {
        return assembler.toResource(purchaseService.getById(id));
    }

    @GetMapping("/purchases")
    public Resources<PurchaseResource> getAll() {

        List<PurchaseResource> purchaseResourcesList =
                assembler.toResources(purchaseService.getAll());

        Resources<PurchaseResource> purchaseResources =
                new Resources<PurchaseResource>(purchaseResourcesList);

        purchaseResources
                .add(linkTo(methodOn(PurchaseController.class).getAll()).withSelfRel());

        return purchaseResources;
    }

    /*
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

    @GetMapping("/purchases/{id}")
    ProductResource getById(@PathVariable Long id) {
        return assembler.toResource(productService.getById(id));
    }
    */

}
