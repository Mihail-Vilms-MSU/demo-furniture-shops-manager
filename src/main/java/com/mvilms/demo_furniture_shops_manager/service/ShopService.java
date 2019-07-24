package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ShopRepository;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShopService {
    @Autowired
    private ShopRepository repository;


    /**
     * Returns shop record from database by its ID
     *
     * @param id Shop record's ID
     * @return Shop record
     */
    public Shop getById(String id){
        return repository.getOne(id);
    }


    /**
     *
     * @param pageable
     * @return
     */
    public Page<Shop> getAll(Pageable pageable){ return repository.findAll(pageable); }


    /**
     *
     * @param searchInput
     * @param pageable
     * @return
     */
    public Page<Shop> findByAllFields(String searchInput, Pageable pageable){
        return repository.liveSearch(searchInput, pageable);
    }


    /**
     *
     * @param name
     * @param state
     * @param city
     * @param pageable
     * @return
     */
    public Page<Shop> advancedSearch(String name, String state, String city, Pageable pageable){
        return repository.advancedSearch(name, state, city, pageable);
    }

    /**
     *
     * @param newShop
     * @return
     */
    public Shop save(Shop newShop){ return repository.save(newShop); }


    /**
     *
     * @param id
     */
    public void delete(String id) { repository.deleteById(id); }


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
}
