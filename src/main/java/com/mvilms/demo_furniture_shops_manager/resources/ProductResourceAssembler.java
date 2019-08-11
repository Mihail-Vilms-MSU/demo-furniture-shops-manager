package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.web.ProductController;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {

    public ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toResource(Product product) {
        ProductResource productResource = new ProductResource(product);

        productResource.add(linkTo(methodOn(ProductController.class).getById(product.getId())).withSelfRel());

//        productResource.add(linkTo(methodOn(ProductController.class).getAll("", PageRequest.of(0, 10))).withSelfRel());

        //        purchaseResource.add(linkTo(methodOn(PurchaseController.class)
//                .getAll()).withRel("products"));


        return productResource;
    }
}
