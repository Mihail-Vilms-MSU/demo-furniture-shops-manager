package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.data.EmployeeRepository;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.web.EmployeeController;
import com.mvilms.demo_furniture_shops_manager.web.ShopController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class ShopResourceAssembler extends ResourceAssemblerSupport<Shop, ShopResource> {
    public ShopResourceAssembler() {
        super(ShopController.class, ShopResource.class);
    }

    @Override
    public ShopResource toResource(Shop shop) {
        ShopResource shopResource = new ShopResource(shop);

        shopResource.add(linkTo(methodOn(ShopController.class).getById(shop.getId())).withSelfRel());

        shopResource.add(linkTo(methodOn(ShopController.class)
                .getAll("", PageRequest.of(0, 10))).withRel("shops"));

        shopResource.add(linkTo(methodOn(ShopController.class).getProductsInShop(shop.getId())).withRel("stock"));

        /*
        shopResource
            .add(linkTo(methodOn(EmployeeRepository.class).findByShopId(shop.getId(), PageRequest.of(0, 5, Sort.by("name").descending())))
            .withRel("shops/employees"));
        */
        return shopResource;
    }
}
