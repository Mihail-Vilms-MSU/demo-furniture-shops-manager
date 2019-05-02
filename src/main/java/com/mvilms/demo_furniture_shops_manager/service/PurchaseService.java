package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.PurchaseRepository;
import com.mvilms.demo_furniture_shops_manager.data.PurchaseToProductRepository;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import com.mvilms.demo_furniture_shops_manager.model.PurchaseToProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseToProductRepository purchaseToProductRepository;

    @Autowired
    ShopService shopService;
    @Autowired
    EmployeeService employeeService;


    public Purchase getById(Long id) {
        return purchaseRepository.getOne(id);
    }

    public List<Purchase> getAll(){
        return purchaseRepository.findAll();
    }

    ////////////////////////////////////////////////////////

    public Purchase save(Purchase newPurchase){
        return purchaseRepository.save(newPurchase);
    }

    public PurchaseToProduct savePurchaseProduct(PurchaseToProduct purchaseToProduct){
        return purchaseToProductRepository.save(purchaseToProduct);
    }

    /**
     *
     * @param purchase
     * @return
     */
    /*
    Long getShopId(Purchase purchase){
        Long employeeId = purchase.getEmployeeId();
        Employee employee = employeeService.getById(employeeId);
        Long shopId = employee.getShopId();
        return shopId;
    }
    */
    /**
     *
     * @param newPurchase
     * @param amountOfProducts
     * @return
     */
    /*
    public Boolean createPurchase(Purchase newPurchase, Map<Long, Long> amountOfProducts){
        Long shopId = getShopId(newPurchase);
        Boolean isEnoughAmount = shopService.hasEnoughAmountOfProducts(shopId, amountOfProducts);
        if (!isEnoughAmount) return false;

        Purchase savedPurchase = save(newPurchase);

        amountOfProducts.entrySet().stream()
            .forEach(amountOfProduct -> {
                Long productId = amountOfProduct.getKey();
                Long amount = amountOfProduct.getValue();
                savePurchaseProduct(new PurchaseToProduct(savedPurchase.getId(), productId, amount));
            });

        return shopService.withdrawProducts(shopId, amountOfProducts);
    }
    */
}
