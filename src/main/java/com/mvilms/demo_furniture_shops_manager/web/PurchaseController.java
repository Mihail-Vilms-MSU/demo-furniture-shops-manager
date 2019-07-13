package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import com.mvilms.demo_furniture_shops_manager.model.PurchaseToProduct;
import com.mvilms.demo_furniture_shops_manager.resources.PurchaseResource;
import com.mvilms.demo_furniture_shops_manager.resources.PurchaseResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class PurchaseController {
    @Autowired
    private PurchaseResourceAssembler assembler;
    @Autowired
    private PurchaseService service;

    /**
     * Returns all records in database in table "purchases"
     *
     * @param id
     * @return
     */
    @GetMapping("/purchases/{id}")
    public PurchaseResource getById(@PathVariable String id) {
        return assembler.toResource(service.getById(id));
    }


    /**
     *
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    @GetMapping("/purchases")
    public PagedResources<PurchaseResource> getAll(Pageable pageable) {
        Page page = service.getAll(pageable);

        return PagedResourcesBuilder.<Purchase, PurchaseResource>build(page, assembler);
    }


    /**
     *
     * @param purchaseId
     * @return
     */
    @GetMapping("/purchases/{purchaseId}/products")
    public List<PurchaseToProduct> getPurchasePositions(@PathVariable String purchaseId){
        return service.getPurchasePositions(purchaseId);
    }


    /**
     * Registers new purchase in system
     *
     * @param strProductsMap
     * @param shopId Shop id purchase was registered in
     * @param employeeId Employee id purchase was registered by
     * @return
     */
    @PostMapping("/purchases")
    public ResponseEntity<?> savePurchase(@RequestBody String strProductsMap,
                                          @RequestParam String shopId,
                                          @RequestParam String employeeId){

        JSONObject productsMap = new JSONObject(strProductsMap);
        service.createPurchase(shopId, employeeId, productsMap);
        return ResponseEntity.noContent().build();
    }
}
