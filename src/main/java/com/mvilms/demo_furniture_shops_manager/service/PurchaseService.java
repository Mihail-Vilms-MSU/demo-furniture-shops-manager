package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.PurchaseRepository;
import com.mvilms.demo_furniture_shops_manager.data.PurchaseToProductRepository;
import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import com.mvilms.demo_furniture_shops_manager.model.PurchaseToProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PurchaseService {
    private final PurchaseRepository repository;
    private final PurchaseToProductRepository purchaseToProductRepository;
    private final ProductService productService;

    public PurchaseService(PurchaseRepository purchaseRepository, PurchaseToProductRepository purchaseToProductRepository,
                           ProductService productService) {
        this.repository = purchaseRepository;
        this.purchaseToProductRepository = purchaseToProductRepository;
        this.productService = productService;
    }

    //////////////////////////////////////////////////////////////////////

    public Purchase getById(String id) {
        return repository.getOne(id);
    }

    public Page getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Purchase save(Purchase newPurchase){
        return repository.save(newPurchase);
    }

    public PurchaseToProduct savePurchaseProduct(PurchaseToProduct purchaseToProduct){
        return purchaseToProductRepository.save(purchaseToProduct);
    }

    //////////////////////////////////////////////////////////////////////

    public List<PurchaseToProduct> getPurchasePositions(String purchaseId){
        return purchaseToProductRepository.getProductsInPurchase(purchaseId);
    }

    //////////////////////////////////////////////////////////////////////

    public Boolean createPurchase(Purchase newPurchase, Map<String, Long> amountOfProducts){
        Purchase savedPurchase = save(newPurchase);

        amountOfProducts.entrySet().stream()
            .forEach(amountOfProduct -> {
                String productId = amountOfProduct.getKey();
                Long amount = amountOfProduct.getValue();
                savePurchaseProduct(new PurchaseToProduct(savedPurchase, productService.getById(productId), amount));
            });

        return true;
    }

}
