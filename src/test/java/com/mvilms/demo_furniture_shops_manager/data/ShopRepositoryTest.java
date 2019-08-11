package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Shop;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ShopRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShopRepository shopRepository;

    @Before
    public void setupTestData() {
        Shop shop1 = new Shop("Shop1", "City1", "State1", "Address11", "111 111 1111");
        entityManager.persist(shop1);

        Shop shop2 = new Shop("Shop2", "City1", "State1", "Address12", "222 222 2222");
        entityManager.persist(shop2);

        Shop shop3 = new Shop("Shop3", "City3", "State3", "Address3", "333 333 3333");
        entityManager.persist(shop3);

        Shop shop4 = new Shop("Shop4", "City4", "State3", "Address4", "444 444 4444");
        entityManager.persist(shop4);

        Shop shop5 = new Shop("Shop5", "City5", "State3", "Address5", "555 555 5555");
        entityManager.persist(shop5);

        entityManager.flush();
    }

    @Test
    public void findAll_dbHas5Records_returns5Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Shop> shopPage = shopRepository.findAll(pageable);
        Assert.assertEquals(5, shopPage.getContent().size());
    }

    @Test
    public void findByState_dbHas5Records_returns3Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Shop> shopPage = shopRepository.findByState("State3", pageable);
        Assert.assertEquals(3, shopPage.getContent().size());
    }

    @Test
    public void findByCity_dbHas5Records_return2Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Shop> shopPage = shopRepository.findByCity("City1", pageable);
        Assert.assertEquals(2, shopPage.getContent().size());
    }

    @Test
    public void getListOfStates_dbHas5Records_return2Records(){
        Assert.assertEquals(2, shopRepository.getListOfStates().size());
    }

    @Test
    public void getListOfCities_dbHas5Records_return3Records(){
        Assert.assertEquals(3, shopRepository.getListOfCities("State3").size());
    }

    @Test
    public void liveSearch_cityAsParam_returns2Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Shop> shopPage = shopRepository.liveSearch("City1", pageable);
        Assert.assertEquals(2, shopPage.getContent().size());
    }

    @Test
    public void liveSearch_stateAsParam_returns3Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Shop> shopPage = shopRepository.liveSearch("State3", pageable);
        Assert.assertEquals(3, shopPage.getContent().size());
    }

    @Test
    public void advancedSearch_returns1Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Shop> shopPage = shopRepository.advancedSearch("Shop3", "State3","City3", pageable);
        Assert.assertEquals(1, shopPage.getContent().size());
    }
}
