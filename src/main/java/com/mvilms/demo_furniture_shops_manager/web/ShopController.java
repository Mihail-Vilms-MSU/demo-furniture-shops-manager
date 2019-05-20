package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import com.mvilms.demo_furniture_shops_manager.resources.AmountResource;
import com.mvilms.demo_furniture_shops_manager.resources.AmountResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.resources.ShopResource;
import com.mvilms.demo_furniture_shops_manager.resources.ShopResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.ShopService;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ShopController {
    private final ShopService service;
    private final ShopResourceAssembler assembler;
    private final AmountResourceAssembler amountAssembler;
    public ShopController(ShopService service, ShopResourceAssembler assembler, AmountResourceAssembler amountAssembler) {
        this.service = service;
        this.assembler = assembler;
        this.amountAssembler = amountAssembler;
    }

    /////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/shops/{id}")
    public ShopResource getById(@PathVariable String id) {
        return assembler.toResource(service.getById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/shops")
    public Resources<ShopResource> getAll() {
        List<Shop> shops = service.getAll();

        List<ShopResource> shopResourceList =
                assembler.toResources(service.getAll());

        Resources<ShopResource> shopResources =
                new Resources<ShopResource>(shopResourceList);

        shopResources
                .add(linkTo(methodOn(ShopController.class).getAll()).withSelfRel());

        return shopResources;
    }

    /////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/shops")
    public ShopResource addNew(@RequestBody Shop newShop) {
        return assembler.toResource(service.save(newShop));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/shops/{id}")
    public ShopResource update(@RequestBody Shop newShop, @PathVariable String id) throws URISyntaxException {
        Shop savedShop;

        Shop oldShop = service.getById(id);

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

        savedShop = service.save(oldShop);

        return assembler.toResource(savedShop);
    }

    /////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/shops/{shop_id}/products/{product_id}")
    public AmountResource getAmountOfProduct(@PathVariable String shop_id, @PathVariable String product_id) {
        ShopToProduct amountEntry = service.getAmountOfProduct(shop_id, product_id);
        return amountAssembler.toResource(amountEntry);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/shops/{shop_id}/products")
    Resources<AmountResource> getProductsInShop(@PathVariable String shop_id, Pageable pageable) {
        Page page = service.getAmountOfProductsForShop(shop_id, pageable);

        List<AmountResource> amountResourcesList = (List) page.getContent()
            .stream()
            .map(amountEntry -> {
                return amountAssembler.toResource((ShopToProduct) amountEntry);
            })
            .collect(Collectors.toList());

        PagedResources.PageMetadata pageMetadata =
            new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());

        return new PagedResources<>(amountResourcesList, pageMetadata);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/shops/{shopId}/products")
    ResponseEntity<?> addAmountOfProducts(@RequestBody String stringAmountJson, @PathVariable String shopId) {
        JSONObject amountJson = new JSONObject(stringAmountJson);

        Map<String, Long> productAmountMap = new HashMap<>();
        amountJson.keySet()
            .forEach(productId -> {
                productAmountMap.put(productId, amountJson.getLong(productId));
            });

        service.addProducts(shopId, productAmountMap);

        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/shops/{shopId}/products/{productId}")
    ResponseEntity<?> addAmountOfProduct(@RequestBody Long amount, @PathVariable String shopId, @PathVariable String productId) {
        service.addAmountOfProduct(shopId, productId, amount);

        return ResponseEntity.noContent().build();
    }

}
