package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import com.mvilms.demo_furniture_shops_manager.model.PurchaseToProduct;
import com.mvilms.demo_furniture_shops_manager.resources.PurchaseResource;
import com.mvilms.demo_furniture_shops_manager.resources.PurchaseResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class PurchaseController {
    private final PurchaseResourceAssembler assembler;
    private final PurchaseService service;
    public PurchaseController(PurchaseResourceAssembler assembler, PurchaseService service) {
        this.assembler = assembler;
        this.service = service;
    }

    //////////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/purchases/{id}")
    public PurchaseResource getById(@PathVariable String id) {
        return assembler.toResource(service.getById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/purchases")
    public PagedResources<PurchaseResource> getAll(Pageable pageable) {
        Page page = service.getAll(pageable);

        List<PurchaseResource> purchaseListResources = (List) page.getContent()
                .stream()
                .map(purchase -> {
                    return assembler.toResource((Purchase) purchase);
                })
                .collect(Collectors.toList());

        PagedResources.PageMetadata pageMetadata =
                new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());

        return new PagedResources <PurchaseResource> (purchaseListResources, pageMetadata);
    }

    //////////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/purchases/{purchaseId}/products")
    public List<PurchaseToProduct> getPurchasePositions(@PathVariable String purchaseId){
        return service.getPurchasePositions(purchaseId);
    }

    //#TODO
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/purchases")
    public ResponseEntity<?> savePurchase(@RequestBody String strJsonBody, @PathVariable String purchaseId){
        JSONObject jsonBody = new JSONObject(strJsonBody);

        Purchase newPurchase = (Purchase) jsonBody.get("purchase");



        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping("/products")
    Resources<ProductResource> getAll() {

        List<ProductResource> productResourcesList =
                assembler.toResources(productService.getAll());

        Resources<ProductResource> productResources =
                new Resources<ProductResource>(productResourcesList);

        productResources
                .add(linkTo(methodOn(ProductController.class).getAll()).withSelfRel());

        return productResources;
    }

    @GetMapping("/purchases/{id}")
    ProductResource getById(@PathVariable Long id) {
        return assembler.toResource(productService.getById(id));
    }
    */

}
