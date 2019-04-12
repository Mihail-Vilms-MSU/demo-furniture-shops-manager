package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ShopRepository;
import com.mvilms.demo_furniture_shops_manager.data.ShopToProductRepository;
import com.mvilms.demo_furniture_shops_manager.exceptions.ShopNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    ShopToProductRepository shopToProductRepository;

    public Shop getShopById(Long id) throws ShopNotFoundException {
        return shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
    }

    public List<Shop> getAllShops(){
        return shopRepository.findAll();
    }

    public Shop addShop(Shop newShop){
        return shopRepository.save(newShop);
    }

    /**
     * Returns map with key corresponds to product ID
     * and value corresponds to available amount of this product within shop
     *
     * @param {Long} id Shop ID we want to know available amount of products for
     * @return {Map} Product id: amount of units available
     */
    public Map<Long, Long> getListOfProducts(Long shopId){
        return shopToProductRepository.findAllProductsForShop(shopId)
                .stream()
                .collect(Collectors.toMap(ShopToProduct::getProductId, ShopToProduct::getAmount));
    }

    /*
    public Map<Long, Long> getListOfProducts(Long id, List<Long> productIdsList){
        return productIdsList.stream()
                .collect();

    }
    */
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
