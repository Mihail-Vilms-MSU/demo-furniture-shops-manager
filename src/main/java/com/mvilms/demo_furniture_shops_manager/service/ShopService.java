package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ShopRepository;
import com.mvilms.demo_furniture_shops_manager.data.ShopToProductRepository;
import com.mvilms.demo_furniture_shops_manager.exceptions.ProductNotFoundException;
import com.mvilms.demo_furniture_shops_manager.exceptions.ShopNotFoundException;
import com.mvilms.demo_furniture_shops_manager.exceptions.ShortageOfProduct;
import com.mvilms.demo_furniture_shops_manager.model.Product;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopService {
    @Autowired
    ShopRepository shopRepository;

    public Shop getById(Long id) throws ShopNotFoundException {
        return shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
    }

    public List<Shop> getAll(){
        return shopRepository.findAll();
    }

    public Shop save(Shop newShop){
        return shopRepository.save(newShop);
    }

    public void delete(Long id) {
        shopRepository.deleteById(id);
    }

//////////////////////////////////////////////////////////////////////////////////////

    /**
     * Finds out how many items of particular product there are at shop
     *
     * @param shopId
     * @param productId
     * @return Available amount of product in shop
     */
    /*
    public Long getAmountOfProduct(Long shopId, Long productId){
        Long amount = shopToProductRepository.getAmount(shopId, productId);
        return (amount != null) ? amount: (long)0;
    }
    */

    /**
     * Returns map with key corresponds to product ID
     * and value corresponds to available amount of this product within shop
     *
     * @param shopId Shop ID we want to know available amount of products for
     * @return {product id: amount of units available}
     */
    /*
    public Map<Long, Long> getAmountOfProductsMap(Long shopId){
        System.out.println("getAmountOfProductsMap shopId: " + shopId);
        return shopToProductRepository.findAll(shopId)
                .stream()
                .collect(Collectors.toMap(ShopToProduct::getProductId, ShopToProduct::getAmount));
    }
    */
    /*
    public List<ShopToProduct> getAmountOfProducts(Long shopId){
        return shopToProductRepository.findAll(shopId);
    }
    */
    /** ---DONE!!!
     * Saves value defines new amount of product available at shop
     * Inserts new record if there was no one with specified shop and product ids
     *
     * @param shopId
     * @param ProductId
     * @param newValue
     * @return Saved record
     */
    /*
    private ShopToProduct saveNewAmountOfProduct(Long shopId, Long ProductId, Long newValue){
        ShopToProduct oldRecord = shopToProductRepository.getRecord(shopId, ProductId);
        if (oldRecord == null){
            return shopToProductRepository.save(new ShopToProduct(shopId, ProductId, newValue));
        }
        oldRecord.setAmount(newValue);
        return shopToProductRepository.save(oldRecord);
    }
    */
    /** ---DONE!!!
     * Changes amount of product available at shop
     *
     * @param shopId
     * @param productId
     * @param diff Can be positive or negative value
     * @return Current amount of product
     */
    /*
    public Long changeAmountOfProduct(Long shopId, Long productId, Long diff) throws ShortageOfProduct{
        Long oldAmount = getAmountOfProduct(shopId, productId);
        ShopToProduct newRecord;

        if (diff == 0) return oldAmount;
        if (diff + oldAmount < 0) throw new ShortageOfProduct(shopId, productId);
        newRecord = saveNewAmountOfProduct(shopId, productId, oldAmount + diff);

        return newRecord.getAmount();
    }
    */
    /**
     * Changes amount of different products units in shop storage
     *
     * @param shopId Shop we want to add products to
     * @param productsDiffMap Map with key corresponds to product ID
     *                     and value corresponds to amount of units we want to add/withdraw
     * @return
     */
    /*
    public void changeAmountOfProducts(Long shopId, Map<Long, Long> productsDiffMap){
        productsDiffMap.entrySet().stream()
            .forEach(productDiff -> {
                Long productId = productDiff.getKey();
                Long diff = productDiff.getValue();

                try {
                    changeAmountOfProduct(shopId, productId, diff);
                } catch (ShortageOfProduct exception) {
                    log.error("Lack of product in storage. Product id: " + productId);
                }
            });
    }
    */
}
