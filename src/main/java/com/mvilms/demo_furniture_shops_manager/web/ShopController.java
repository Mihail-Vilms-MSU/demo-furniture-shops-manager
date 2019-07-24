package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import com.mvilms.demo_furniture_shops_manager.resources.AmountResource;
import com.mvilms.demo_furniture_shops_manager.resources.AmountResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.resources.ShopResource;
import com.mvilms.demo_furniture_shops_manager.resources.ShopResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.AmountService;
import com.mvilms.demo_furniture_shops_manager.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
public class ShopController {
    private final ShopService service;
    private final ShopResourceAssembler assembler;
    private final AmountResourceAssembler amountAssembler;
    private final AmountService amountService;

    public ShopController(ShopService service, ShopResourceAssembler assembler, AmountResourceAssembler amountAssembler, AmountService amountService) {
        this.service = service;
        this.assembler = assembler;
        this.amountAssembler = amountAssembler;
        this.amountService = amountService;
    }


    /**
     * Returns shop record by its ID
     *
     * @param id Employee record's Id
     * @return Employee record
     */
    @GetMapping("/shops/{id}")
    public ShopResource getById(@PathVariable String id) {
        return assembler.toResource(service.getById(id));
    }


    /**
     * Takes no arguments or one argument - string in order to search records by multiple fields
     * In first case method returns all records in database in table "employees"
     * In second case method returns records that contains input value in one of theirs fields
     *
     * @param searchInput String to search
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    @GetMapping("/shops")
    public Resources<ShopResource> getAll(
            @RequestParam(value ="searchInput", required = false, defaultValue = "") String searchInput,
            Pageable pageable
    ) {
        Page page;
        if (searchInput.equals("")){
            page = service.getAll(pageable);
        }
        page = service.findByAllFields(searchInput, pageable);

        return PagedResourcesBuilder.<Shop, ShopResource>build(page, assembler);
    }


    /**
     *
     * @param name
     * @param state
     * @param city
     * @param pageable
     * @return
     */
    @GetMapping("/shops/advancedSearch")
    public Resources<ShopResource> advancedSearch(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "state", required = false, defaultValue = "") String state,
            @RequestParam(value = "city", required = false, defaultValue = "") String city,
            Pageable pageable
    ) {
        Page page = service.advancedSearch(name, state, city, pageable);
        return PagedResourcesBuilder.<Shop, ShopResource>build(page, assembler);
    }


    /**
     * Creates new shop record in database
     *
     * @param newShop POST Request body with new record fields values
     * @return Saved record
     */
    @PostMapping("/shops")
    public ShopResource addNew(@RequestBody Shop newShop) {
        return assembler.toResource(service.save(newShop));
    }


    /**
     *
     * @param newShop
     * @param id
     * @return
     * @throws URISyntaxException
     */
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


    /**
     *
     * @return
     */
    @GetMapping("/shops/search-params/state")
    List<String> getListOfStates(){
        return service.getListOfStates();
    }


    /**
     *
     * @param state
     * @return
     */
    @GetMapping("/shops/search-params/city")
    List<String> getListOfCities(@RequestParam(value ="state", required = false, defaultValue = "") String state){
        return service.getListOfCities(state);
    }


    /**
     * Get entry that define available amount of  particular product at particular shop
     *
     * @param shop_id Shop record's ID
     * @param product_id Product record's ID
     * @return Entry with information on available amount of product
     */
    @GetMapping("/shops/{shop_id}/products/{product_id}")
    public AmountResource getAmountOfProduct(@PathVariable String shop_id, @PathVariable String product_id) {
        ShopToProduct amountEntry = amountService.getAmountEntry(shop_id, product_id);

        return amountAssembler.toResource(amountEntry);
    }


    /**
     * Returns list of records that correspond to amount of every product available at shop
     *
     * @param shop_id Shop record's ID
     * @return Available amount of every product in shop's storage
     */
    @GetMapping("/shops/{shop_id}/products")
    public List<AmountResource> getProductsInShop(@PathVariable String shop_id) {
        return amountAssembler.listToResource(amountService.getAmountsForShop(shop_id));
    }


    /**
     * Method adds defined amount of several product positions to shop's storage
     *
     * @param stringAmountJson JSON where key serves as product id and value as
     * @param shopId Shop record's ID
     * @return Available amount of every product in shop's storage after fulfillment
     */
    @PostMapping("/shops/{shopId}/products")
    public List<AmountResource> addAmountOfProducts(@RequestBody String stringAmountJson, @PathVariable String shopId) {
        JSONObject amountJson = new JSONObject(stringAmountJson);

        Map<String, Long> productAmountMap = new HashMap<>();
        amountJson.keySet()
            .forEach(productId -> {
                productAmountMap.put(productId, amountJson.getLong(productId));
            });
        amountService.addProducts(shopId, productAmountMap);

        return getProductsInShop(shopId);
    }
}
