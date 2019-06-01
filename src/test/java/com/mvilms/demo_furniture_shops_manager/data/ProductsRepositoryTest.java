package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Product;
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

import java.math.BigDecimal;

/* #READ https://www.baeldung.com/spring-boot-testing */

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ProductsRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setupTestData() {
        Product sofa1 = new Product("Sofa1", new BigDecimal("400.00"), "Sofa");
        entityManager.persist(sofa1);

        Product sofa2 = new Product("Sofa2", new BigDecimal("300.00"), "Sofa");
        entityManager.persist(sofa2);

        Product chair1 = new Product("Chair1", new BigDecimal("100.00"), "Chair");
        entityManager.persist(chair1);

        Product chair2 = new Product("Chair2", new BigDecimal("150.00"), "Chair");
        entityManager.persist(chair2);

        Product chair3 = new Product("Chair3", new BigDecimal("200.00"), "Chair", false);
        entityManager.persist(chair3);

        entityManager.flush();
    }

    @Test
    public void findAll_dbHas5Records_returns5Records() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = productRepository.findAll(pageable);
        Assert.assertEquals(5, productPage.getContent().size());
    }

    @Test
    public void findByName_dbHas2RecordsWithNameLikeSofa_returns2Records() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = productRepository.findByName("Sofa", pageable);
        Assert.assertEquals(2, productPage.getContent().size());
    }

    @Test
    public void findByType_dbHas3RecordsWithTypeChair_returns3Records() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = productRepository.findByType("Chair", pageable);
        Assert.assertEquals(3, productPage.getContent().size());
    }

    @Test
    public void findByIsActive_dbHas4ActiveRecords_returns4Records() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = productRepository.findByActive(true, pageable);
        Assert.assertEquals(4, productPage.getContent().size());
    }

    @Test
    public void findByPrice_dbHasWithPriceBetween120And250returns3Records() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = productRepository.findByPrice(new BigDecimal(120), new BigDecimal(350), pageable);
        Assert.assertEquals(3, productPage.getContent().size());
    }

    @Test
    public void findMinPrice_returns100AsMinPrice() {
        Assert.assertEquals(0, productRepository.findMinPrice().compareTo(new BigDecimal(100)));
    }

    @Test
    public void findMinPrice_returns400AsMaxPrice() {
        Assert.assertEquals(0, productRepository.findMaxPrice().compareTo(new BigDecimal(400)));
    }
}
