package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ShopResourceAssembler implements ResourceAssembler<Shop, Resource<Shop>> {
    @Override
    public Resource<Shop> toResource(Shop shop) {
        return new Resource<>(shop,
                linkTo(methodOn(ShopController.class).getById(shop.getId())).withSelfRel(),
                linkTo(methodOn(ShopController.class).getAll()).withRel("shops"),
                linkTo(methodOn(ShopController.class).getProductsInShop(shop.getId())).withRel("products")
        );
    }

}
