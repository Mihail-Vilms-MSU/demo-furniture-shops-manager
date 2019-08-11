package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.ProductRepository;
import com.mvilms.demo_furniture_shops_manager.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

// #READ https://www.baeldung.com/spring-boot-testing

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @TestConfiguration
    static class ProductServiceTestContextConfiguration {
        @Bean
        public ProductService productService() {
            return new ProductService();
        }
    }

    @Autowired
    private ProductService productService;

    @MockBean
    ProductRepository productRepository;


    @Before
    public void setUp_forFindByName() {
        List<Product> productsListByName = new ArrayList<>();
        productsListByName.add(new Product("Sofa1", new BigDecimal("400.00"), "Sofa"));
        productsListByName.add(new Product("Sofa2", new BigDecimal("300.00"), "Sofa"));

        Mockito.when(productRepository.findByName("Sofa", PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(productsListByName));
    }


    @Before
    public void setUp_forFindByType() {
        List<Product> productsListByType = new ArrayList<>();
        productsListByType.add(new Product("Chair1", new BigDecimal("400.00"), "Chair"));
        productsListByType.add(new Product("Chair2", new BigDecimal("300.00"), "Chair"));
        productsListByType.add(new Product("Chair3", new BigDecimal("200.00"), "Chair"));

        Mockito.when(productRepository.findByType("Chair", PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(productsListByType));

    }


//    @Test
//    public void findByName_returns2Records(){
//        assertThat(productService.findByName("Sofa", PageRequest.of(0, 10)).getContent().size()).isEqualTo(2);
//    }


//    @Test
//    public void findByName_returns3Records(){
//        List<Product> productsList = productService.findByType("Chair", PageRequest.of(0, 10)).getContent();
//        assertThat(productsList.size()).isEqualTo(3);
//        assertThat(productsList.get(0).getType()).isEqualTo("Chair");
//    }

}
