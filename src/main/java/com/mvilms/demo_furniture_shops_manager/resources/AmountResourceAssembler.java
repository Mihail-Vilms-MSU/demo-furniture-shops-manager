package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import com.mvilms.demo_furniture_shops_manager.web.ShopController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class AmountResourceAssembler extends ResourceAssemblerSupport<ShopToProduct, AmountResource> {
    public AmountResourceAssembler() {
        super(ShopController.class, AmountResource.class);
    }

    @Override
    public AmountResource toResource(ShopToProduct shopToProduct) {
        AmountResource amountResource = new AmountResource(shopToProduct);

//        amountResource.add(linkTo(methodOn(ShopController.class)
//                .getAmountOfProduct(shopToProduct.getShop().getId(), shopToProduct.getProduct().getId())).withSelfRel());

        // amountResource.add(linkTo(methodOn(ShopController.class).getById(employee.getShop().getId())).withRel("shop"));

        return amountResource;
    }
}

