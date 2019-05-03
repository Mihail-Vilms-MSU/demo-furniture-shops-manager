package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.exceptions.ProductNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import com.mvilms.demo_furniture_shops_manager.resources.*;
import com.mvilms.demo_furniture_shops_manager.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    ShopResourceAssembler assembler;

    // #TODO#
    // Implement "NOT_FOUND" Exception
    @GetMapping("/shops/{id}")
    public ShopResource getById(@PathVariable Long id) {
        return assembler.toResource(shopService.getById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/shops")
    public Resources<ShopResource> getAll() {
        List<Shop> shops = shopService.getAll();

        List<ShopResource> shopResourceList =
                assembler.toResources(shopService.getAll());

        Resources<ShopResource> shopResources =
                new Resources<ShopResource>(shopResourceList);

        shopResources
                .add(linkTo(methodOn(ShopController.class).getAll()).withSelfRel());

        return shopResources;
    }
    /*
    @GetMapping("/shops/{id}/employees")
    public Page<Employee> getEmployees(@PathVariable Long id) {
        return shopService.getEmployees(id);
    }
    */
    /////////////////////////////////////////////////////////////////////

    @PostMapping("/shops")
    public ShopResource addNew(@RequestBody Shop newShop) {
        return assembler.toResource(shopService.save(newShop));
    }

    @PutMapping("/shops/{id}")
    public ShopResource update(@RequestBody Shop newShop, @PathVariable Long id) throws URISyntaxException {
        Shop savedShop;

        try {
            Shop oldShop = shopService.getById(id);

            if (newShop.getName() != null)
                oldShop.setName(newShop.getName());
            if (newShop.getCity() != null)
                oldShop.setCity(newShop.getCity());
            if (newShop.getState() != null)
                oldShop.setState(newShop.getState());
            if (newShop.getAddress() != null)
                oldShop.setAddress(newShop.getAddress());
            if (newShop.getPhone() != null)
                oldShop.setPhone(newShop.getPhone());

            savedShop = shopService.save(oldShop);
        } catch (ProductNotFoundException exception){ // haven't found existing product record
            savedShop = shopService.save(newShop);
        }

        return assembler.toResource(savedShop);
    }

    /*
    @GetMapping("/shops/{shop_id}/products")
    Resources<ProductAmountResource> getProductsInShop(@PathVariable Long shop_id) {
        List<ShopToProduct> productsList = shopService.getAmountOfProducts(shop_id);
        log.info("~~~ In ShopController: getProductsInShop(): shop_id: " + shop_id);
        log.info("~~~ productsList: " + productsList);
        List<ProductAmountResource> productAmountResourceList
                = new ProductAmountResourceAssembler().toResources(productsList);

        Resources<ProductAmountResource> productAmountResources =
                new Resources<ProductAmountResource>(productAmountResourceList);

        productAmountResources.add(linkTo(methodOn(ShopController.class).getProductsInShop(shop_id)).withSelfRel());

        return productAmountResources;
    }
    */
    /*
    @GetMapping("/shops/{shop_id}/products/{product_id}")
    Long getAmountOfProduct(@PathVariable Long shop_id, @PathVariable Long product_id) {
        return shopService.getAmountOfProduct(shop_id, product_id);
    }

    @PutMapping("/shops/{shopId}/products/{productId}")
    Long setAmountOfProduct(@RequestBody Long diff, @PathVariable Long shopId, @PathVariable Long productId) {
        return shopService.changeAmountOfProduct(shopId, productId, diff);
    }
    */
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
