package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import com.mvilms.demo_furniture_shops_manager.web.ProductController;
import com.mvilms.demo_furniture_shops_manager.web.ShopController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        String shopId = shopToProduct.getShop().getId();
        String productId = shopToProduct.getProduct().getId();

        amountResource.add(linkTo(methodOn(ShopController.class).getAmountOfProduct(shopId, productId)).withSelfRel());

        amountResource.add(linkTo(methodOn(ShopController.class).getById(shopId)).withRel("shop"));
        amountResource.add(linkTo(methodOn(ProductController.class).getById(productId)).withRel("product"));

        return amountResource;
    }

    public List<AmountResource> listToResource(List<ShopToProduct> shopToProductList) {
        return shopToProductList
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }
}

