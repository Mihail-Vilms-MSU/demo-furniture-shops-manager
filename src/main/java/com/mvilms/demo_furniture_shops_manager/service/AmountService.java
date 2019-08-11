package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ShopToProductRepository;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AmountService {
    @Autowired
    private ShopToProductRepository repository;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;


    /**
     * Finds how many items of particular product there are at shop storage
     *
     * @param shopId Shop's record ID
     * @param productId Product's record ID
     * @return Record that define available amount of particular product in shop
     */
    public ShopToProduct getAmountEntry(String shopId, String productId){
        return repository.findOneRecord(shopId, productId);
    }


    /**
     * Returns all entries with information about amount of products that corresponds to particular shop
     *
     * @param shopId Shop's record ID we want to know amount of products in
     * @return Records that define amount of different products for particular shop
     */
    public List<ShopToProduct> getAmountsForShop(String shopId){
        return repository.findByShopId(shopId);
    }


    /**
     * Returns all entries with information about amount of particular product available at every shops
     *
     * @param productId Product record's ID
     * @return Records that define amount of product at every shops
     */
    public List<ShopToProduct> getAmountsOfProduct(String productId){
        return repository.findByProductId(productId);
    }


    /**
     * Saves defined amount of particular product at shop
     * Inserts new record if there was no one with specified shop's ID and product's ID
     *
     * @param shopId Shop record's ID
     * @param ProductId Product record's ID
     * @param newValue Value that define new amount of product
     */
    private void saveNewAmountOfProduct(String shopId, String ProductId, Long newValue){
        ShopToProduct oldRecord = repository.findOneRecord(shopId, ProductId);

        if (oldRecord != null && newValue != 0) {
            oldRecord.setAmount(newValue);
            repository.save(oldRecord);
            return;
        }

        if (oldRecord == null && newValue != 0) {
            repository.save(new ShopToProduct(shopService.getById(shopId), productService.getById(ProductId), newValue));
            return;
        }

        if (oldRecord != null && newValue == 0) {
            repository.deleteById(oldRecord.getId());
            return;
        }
    }


    /**
     *
     * @param shopId
     * @param productId
     * @param amount
     * @return
     */
    private Boolean addAmountOfProduct(String shopId, String productId, Long amount){
        ShopToProduct shopToProduct = getAmountEntry(shopId, productId);
        saveNewAmountOfProduct(shopId, productId, getAmountEntry(shopId, productId).getAmount() + amount);

        return true;
    }


    /**
     * Function adds defined amount of product to storage
     *
     * @param shopId
     * @param targetAmounts Key - product id; Value - amount of product to add
     * @return
     */
    public Boolean addProducts(String shopId, Map<String, Long> targetAmounts) {
        targetAmounts.entrySet().stream()
                .forEach(targetAmount -> {
                    String productId = targetAmount.getKey();
                    Long amount = targetAmount.getValue();
                    addAmountOfProduct(shopId,  productId, amount);
                });

        return  true;
    }

    /**
     *
     * @param shopId
     * @param productId
     * @param targetAmount
     * @return
     */
    /*
    public Boolean hasEnoughAmountOfProduct(Long shopId, String productId, String targetAmount){
        Long amountOfProduct = getAmountOfProduct(shopId, productId);
        if (amountOfProduct >= targetAmount) return true;
        return false;
    }
    */

    /**
     *
     * @param shopId
     * @param targetAmountOfProducts
     * @return
     */
    /*
    public Boolean hasEnoughAmountOfProducts(Long shopId, Map<Long, Long> targetAmountOfProducts){
        return targetAmountOfProducts.entrySet().stream()
            .allMatch(amountOfProduct -> {
                Long productId = amountOfProduct.getKey();
                Long amount = amountOfProduct.getValue();
                return hasEnoughAmountOfProduct(shopId, productId, amount);
            });
    }
    */

    /**
     *
     * @param shopId
     * @param targetAmounts
     * @return
     */
    /*
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
    */

}
