package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.web.ShopController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class ShopResourceAssembler extends ResourceAssemblerSupport<Shop, ShopResource> {
    public ShopResourceAssembler() {
        super(ShopController.class, ShopResource.class);
    }

    @Override
    protected ShopResource instantiateResource(Shop shop) {
        return new ShopResource(shop);
    }
    @Override
    public ShopResource toResource(Shop shop) {
        return createResourceWithId(shop.getId(), shop);
    }
}
