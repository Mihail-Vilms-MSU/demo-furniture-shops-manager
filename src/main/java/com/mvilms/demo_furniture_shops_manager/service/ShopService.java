package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ShopRepository;
import com.mvilms.demo_furniture_shops_manager.data.ShopToProductRepository;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShopService {
    private final ShopRepository repository;
    private final ProductService productService;
    private final ShopToProductRepository shopToProductRepository;

    public ShopService(ShopRepository repository, EmployeeService service,
                       ShopToProductRepository shopToProductRepository, ProductService productService) {
        this.repository = repository;
        this.shopToProductRepository = shopToProductRepository;
        this.productService = productService;
    }

    //////////////////////////////////////////////////////////////////////////

    public Shop getById(String id){
        return repository.getOne(id);
    }

    public Page<Shop> getAll(Pageable pageable){ return repository.findAll(pageable); }

    public Page<Shop> findByAllFields(String searchInput, Pageable pageable){
        return repository.liveSearch(searchInput, pageable);
    }

    public Page<Shop> advancedSearch(String name, String state, String city, Pageable pageable){
        return repository.advancedSearch(name, state, city, pageable);
    }

    public Shop save(Shop newShop){ return repository.save(newShop); }

    public void delete(String id) { repository.deleteById(id); }

    ///////////////////////////////////////////////////////////////////////////

    /**
     * Finds how many items of particular product there are at shop storage
     *
     * @param shopId
     * @param productId
     * @return Available amount of product in shop
     */
    public ShopToProduct getAmountOfProduct(String shopId, String productId){
        return shopToProductRepository.findOneRecord(shopId, productId);
    }

    /**
     * Returns all entries with information on amount of products that corresponds to target shop
     *
     * @param shopId Shop record id we want to know amount of products in
     * @param pageable
     * @return
     */
    public Page getAmountOfProductsForShop(String shopId, Pageable pageable){
        return shopToProductRepository.findByShopId(shopId, pageable);
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
     * @param amount
     * @return
     */
    public Boolean addAmountOfProduct(String shopId, String productId, Long amount){
        ShopToProduct shopToProduct = shopToProductRepository.findOneRecord(shopId, productId);
        saveNewAmountOfProduct(shopId, productId, shopToProduct.getAmount() + amount);

        return true;
    }

    /**
     * Saves value defines new amount of product available at shop
     * Inserts new record if there was no one with specified shop and product ids
     *
     * @param shopId
     * @param ProductId
     * @param newValue
     * @return
     */
    private void saveNewAmountOfProduct(String shopId, String ProductId, Long newValue){
        ShopToProduct oldRecord = shopToProductRepository.findOneRecord(shopId, ProductId);

        if (oldRecord != null && newValue != 0) {
            oldRecord.setAmount(newValue);
            shopToProductRepository.save(oldRecord);
            return;
        }

        if (oldRecord == null && newValue != 0) {
            shopToProductRepository
                    .save(new ShopToProduct(this.getById(shopId), productService.getById(ProductId), newValue));
            return;
        }

        if (oldRecord != null && newValue == 0) {
            shopToProductRepository.deleteById(oldRecord.getId());
            return;
        }

        return;
    }

    /**
     * Returns list of all states shops are located at
     *
     * @return List of states
     */
    public List<String> getListOfStates(){
        return repository.getListOfStates();
    }

    /**
     * Returns list of all cities shops are located at
     *
     * @return List of cities
     */
    public List<String> getListOfCities(String state){
        return repository.getListOfCities(state);
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
