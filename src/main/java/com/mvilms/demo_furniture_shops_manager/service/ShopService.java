package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ShopRepository;
import com.mvilms.demo_furniture_shops_manager.data.ShopToProductRepository;
import com.mvilms.demo_furniture_shops_manager.exceptions.ShopNotFoundException;
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
    @Autowired
    ShopToProductRepository shopToProductRepository;

    public Shop getById(Long id) throws ShopNotFoundException {
        return shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
    }

    public List<Shop> getAll(){
        return shopRepository.findAll();
    }

    public Shop add(Shop newShop){
        return shopRepository.save(newShop);
    }

    /**
     * Returns map with key corresponds to product ID
     * and value corresponds to available amount of this product within shop
     *
     * @param shopId Shop ID we want to know available amount of products for
     * @return {Product id: amount of units available}
     */
    public Map<Long, Long> getAmountOfProducts(Long shopId){
        return shopToProductRepository.findAllProductsForShop(shopId)
                .stream()
                .collect(Collectors.toMap(ShopToProduct::getProductId, ShopToProduct::getAmount));
    }

    /**
     * Finds out how many items of particular product there are at shop
     *
     * @param shopId
     * @param productId
     * @return Available amount of product in shop
     */
    public Long getAmountOfProduct(Long shopId, Long productId){
        ShopToProduct record = shopToProductRepository.getProductAmount(shopId, productId);
        if (record != null) return record.getAmount();
        return (long)0;
    }


    /**
     * Adding different amount of product units of different types to shop storage
     *
     * @param shopId Shop we want to add products to
     * @param productsList Map with key corresponds to product ID
     *                     and value corresponds to amount of units we wand to add
     * @return
     */
    /*
    public Map<Long, Long> addProductUnits(Long shopId, Map<Long, Long> productsList){
        return productsList.entrySet().stream()
            .forEach(productAmount -> {
                Long productId = productAmount.getKey();
                Long addAmount = productAmount.getValue();
                ShopToProduct shopToProduct = shopToProductRepository.getProductAmount(shopId, productId);
                if (shopToProduct == null){ // there is no record for this product-shop combination
                    addProductToShop(shopId, productId, addAmount);
                }
                shopToProduct.setAmount(shopToProduct.getAmount() + addAmount);
                shopToProductRepository.save(shopToProduct);
            })
        //.collect(Collectors.toMap(ShopToProduct::getProductId, ));
    }
    */

    /**
     * Adding new product position for specific shop. Defining amount of units
     *
     * @param {Long} shopId
     * @param {Long} productId
     * @param {Long} amount
     * @return {}
     */
    public ShopToProduct addProductToShop(Long shopId, Long productId, Long amount){
        ShopToProduct newProductPosition = new ShopToProduct(shopId, productId, amount);
        return shopToProductRepository.save(newProductPosition);
    }
    /*
    public Map<Long, Long> withdrawProductUnits(Long id, List<Product> productsList){
        return productsList.stream()
                .collect(Collectors.toMap(ShopToProduct::getProductId, ));

    }
    */

}
