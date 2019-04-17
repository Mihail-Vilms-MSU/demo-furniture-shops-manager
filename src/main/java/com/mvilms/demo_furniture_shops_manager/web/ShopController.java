package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import com.mvilms.demo_furniture_shops_manager.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
public class ShopController {
    private final ShopService shopService;
    private final ShopResourceAssembler shopResourceAssembler;

    ShopController(ShopService shopService,
                      ShopResourceAssembler shopResourceAssembler) {

        this.shopService = shopService;
        this.shopResourceAssembler = shopResourceAssembler;
    }

    @GetMapping("/shops")
    Resources<Resource<Shop>> getAll() {
        List<Shop> shops = shopService.getAll();

        List<Resource<Shop>> shopResources = shops.stream()
                .map(shopResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(shopResources,
                linkTo(methodOn(ShopController.class).getAll()).withSelfRel());
    }

    @GetMapping("/shops/{id}")
    Resource<Shop> getById(@PathVariable Long id) {
        Shop shop = shopService.getById(id);
        return shopResourceAssembler.toResource(shop);
    }

    @GetMapping("/shops/{shop_id}/products")
    Resources<Resource<ShopToProduct>> getProductsInShop(@PathVariable Long shop_id) {
        List<Resource<ShopToProduct>> resourcesList = shopService
            .getAmountOfProducts(shop_id)
            .stream()
            .map(amountOfProduct -> {
                return new Resource<>(amountOfProduct);
            })
            .collect(Collectors.toList());

        return new Resources<>(resourcesList);
    }

    @GetMapping("/shops/{shop_id}/products/{product_id}")
    Long getAmountOfProduct(@PathVariable Long shop_id, @PathVariable Long product_id) {
        return shopService.getAmountOfProduct(shop_id, product_id);
    }

    @PutMapping("/shops/{shopId}/products/{productId}")
    Long setAmountOfProduct(@RequestBody Long diff, @PathVariable Long shopId, @PathVariable Long productId) {
        return shopService.changeAmountOfProduct(shopId, productId, diff);
    }
    /*
    @PutMapping("/shops/{shopId}/products")
    Long setAmountOfProducts(@RequestBody JSONArray jsonArray, @PathVariable Long shopId) {



        jsonArray.toList().stream().forEach(
                log.info("");
        );

        inputJson.keySet().forEach(key->{
            Long productId = (long)key.;
            inputMap.put(productId, inputJson.getLong((long)productId));
        });

        // return shopService.changeAmountOfProducts(shopId, inputMap);
    }
    */
}
