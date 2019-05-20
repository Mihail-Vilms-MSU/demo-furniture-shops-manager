package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import com.mvilms.demo_furniture_shops_manager.web.EmployeeController;
import com.mvilms.demo_furniture_shops_manager.web.PurchaseController;
import com.mvilms.demo_furniture_shops_manager.web.ShopController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class PurchaseResourceAssembler extends ResourceAssemblerSupport<Purchase, PurchaseResource> {
    public PurchaseResourceAssembler(){
        super(Purchase.class, PurchaseResource.class);
    };

    @Override
    public PurchaseResource toResource(Purchase purchase) {
        PurchaseResource purchaseResource = new PurchaseResource(purchase);

        purchaseResource.add(linkTo(methodOn(PurchaseController.class)
                .getById(purchase.getId())).withSelfRel());

//        purchaseResource.add(linkTo(methodOn(PurchaseController.class)
//                .getAll()).withRel("purchases"));

        purchaseResource.add(linkTo(methodOn(ShopController.class)
                .getById(purchase.getShop().getId())).withRel("shop"));

        purchaseResource.add(linkTo(methodOn(EmployeeController.class)
                .getById(purchase.getEmployee().getId())).withRel("employee"));

        return purchaseResource;
    }
}
