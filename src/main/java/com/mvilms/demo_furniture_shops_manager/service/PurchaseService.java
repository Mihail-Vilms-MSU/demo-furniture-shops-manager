package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.PurchaseRepository;
import com.mvilms.demo_furniture_shops_manager.data.PurchaseToProductRepository;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import com.mvilms.demo_furniture_shops_manager.model.PurchaseToProduct;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PurchaseService {
    private final PurchaseRepository repository;
    private final PurchaseToProductRepository purchaseToProductRepository;
    private final ProductService productService;
    private final ShopService shopService;
    private final EmployeeService employeeService;

    public PurchaseService(PurchaseRepository purchaseRepository,
                           PurchaseToProductRepository purchaseToProductRepository,
                           ProductService productService,
                           ShopService shopService,
                           EmployeeService employeeService) {
        this.repository = purchaseRepository;
        this.purchaseToProductRepository = purchaseToProductRepository;
        this.productService = productService;
        this.shopService = shopService;
        this.employeeService = employeeService;
    }

    //////////////////////////////////////////////////////////////////////

    public Purchase getById(String id) {
        return repository.getOne(id);
    }

    public Page getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public List<PurchaseToProduct> getPurchasePositions(String purchaseId){
        return purchaseToProductRepository.getProductsInPurchase(purchaseId);
    }

    //////////////////////////////////////////////////////////////////////

    public Purchase save(Purchase newPurchase){
        return repository.save(newPurchase);
    }

    public PurchaseToProduct savePurchaseProduct(PurchaseToProduct purchaseToProduct){
        return purchaseToProductRepository.save(purchaseToProduct);
    }

    //////////////////////////////////////////////////////////////////////

    public void createPurchase(String shopId, String employeeId, JSONObject jsonProductsMap){

        Shop targetShop = shopService.getById(shopId);
        Employee targetEmployee = employeeService.getById(employeeId);

        Purchase newPurchase = new Purchase();
        newPurchase.setShop(targetShop);
        newPurchase.setEmployee(targetEmployee);

        Purchase savedPurchase = repository.save(newPurchase);


        Map<String, Long> productsMap = new HashMap<>();
        jsonProductsMap.keySet()
                .forEach(productId -> {
                    productsMap.put(productId, jsonProductsMap.getLong(productId));
                });

        productsMap.entrySet().stream()
            .forEach(amountOfProduct -> {
                String productId = amountOfProduct.getKey();
                Long amount = amountOfProduct.getValue();
                savePurchaseProduct(new PurchaseToProduct(savedPurchase, productService.getById(productId), amount));
            });
    }
}
