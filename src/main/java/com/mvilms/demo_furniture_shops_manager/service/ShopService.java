package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ShopRepository;
import com.mvilms.demo_furniture_shops_manager.data.ShopToProductRepository;
import com.mvilms.demo_furniture_shops_manager.exceptions.ShopNotFoundException;
import com.mvilms.demo_furniture_shops_manager.exceptions.ShortageOfProduct;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    ShopToProductRepository shopToProductRepository;
    @Autowired
    EmployeeService employeeService;

    public Shop getById(Long id){ return shopRepository.getOne(id); }

    public List<Shop> getAll(){
        return shopRepository.findAll();
    }

    public Shop save(Shop newShop){
        return shopRepository.save(newShop);
    }

    public void delete(Long id) {
        shopRepository.deleteById(id);
    }
    /*
    public Page<Employee> getEmployees(Long id){
        return employeeService.getEmployeesByShopId(id);
    }
    */
    /**
     * Finds out how many items of particular product there are at shop
     *
     * @param shopId
     * @param productId
     * @return Available amount of product in shop
     */
    public Long getAmountOfProduct(Long shopId, Long productId){
        ShopToProduct shopToProduct = shopToProductRepository.findOneRecord(shopId, productId);
        if (shopToProduct == null) return (long)0;
        return shopToProduct.getAmount();
    }

    /**
     *
     * @param shopId
     * @param productId
     * @param targetAmount
     * @return
     */
    public Boolean hasEnoughAmountOfProduct(Long shopId, Long productId, Long targetAmount){
        Long amountOfProduct = getAmountOfProduct(shopId, productId);
        if (amountOfProduct >= targetAmount) return true;
        return false;
    }

    /**
     *
     * @param shopId
     * @param targetAmountOfProducts
     * @return
     */
    public Boolean hasEnoughAmountOfProducts(Long shopId, Map<Long, Long> targetAmountOfProducts){
        return targetAmountOfProducts.entrySet().stream()
            .allMatch(amountOfProduct -> {
                Long productId = amountOfProduct.getKey();
                Long amount = amountOfProduct.getValue();
                return hasEnoughAmountOfProduct(shopId, productId, amount);
            });
    }

    /**
     * Saves value defines new amount of product available at shop
     * Inserts new record if there was no one with specified shop and product ids
     *
     * @param shopId
     * @param ProductId
     * @param newValue
     * @return Saved record
     */
    private void saveNewAmountOfProduct(Long shopId, Long ProductId, Long newValue){
        ShopToProduct oldRecord = shopToProductRepository.findOneRecord(shopId, ProductId);

        if (oldRecord != null && newValue != 0) {
            oldRecord.setAmount(newValue);
            shopToProductRepository.save(oldRecord);
            return;
        }

        if (oldRecord == null && newValue != 0) {
            shopToProductRepository.save(new ShopToProduct(shopId, ProductId, newValue));
            return;
        }

        if (oldRecord != null && newValue == 0) {
            shopToProductRepository.deleteById(oldRecord.getId());
            return;
        }

        return;
    }

    /**
     *
     * @param shopId
     * @param targetAmounts
     * @return
     */
    public Boolean withdrawProducts(Long shopId, Map<Long, Long> targetAmounts){
        Boolean isEnoughProducts = hasEnoughAmountOfProducts(shopId, targetAmounts);
        if (!isEnoughProducts) return false;

        targetAmounts.entrySet().stream()
            .forEach(targetAmount -> {
                Long productId = targetAmount.getKey();
                Long amount = targetAmount.getValue();
                ShopToProduct shopToProduct = shopToProductRepository.findOneRecord(shopId, productId);
                saveNewAmountOfProduct(shopId, productId, shopToProduct.getAmount() - amount);
            });

        return  true;
    }
}
