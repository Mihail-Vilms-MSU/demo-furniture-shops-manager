package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Product;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    Integer PRODUCTS_COUNT = 9;
    Integer PRODUCT_ID = 3;
    String PRODUCT_NAME = "McChicken";
    String POST_BODY =  "{\"name\": \"TestProduct_For_POST_Request\",\n" +
                        "\"price\": 2999.00,\n" +
                        "\"description\": \"TestProduct_For_POST_Request_Description\"\n}";

    String PUT_BODY =  "{\"name\": \"TestProduct_For_PUT_Request\",\n" +
            "\"price\": 3999.00,\n" +
            "\"description\": \"TestProduct_For_PUT_Request_Description\"\n}";

    private String getProductsEndpoint(){
        return "http://localhost:" + port + "/products";
    }

    /**
     * Testing GET request for all products
     */
    @Test
    public void getAll_Should_Return_Correct_Count() throws Exception{  // why do we need throws instructions here(???)
        String stringResponse = this.restTemplate.getForObject(getProductsEndpoint(), String.class);
        JSONArray responseJsonArray = new JSONObject(stringResponse)
                .getJSONObject("_embedded")
                .getJSONArray("productList");

        assertThat(responseJsonArray.length()).isEqualTo(PRODUCTS_COUNT);
    }

    /**
     * Testing GET request for one item
     */
    @Test
    public void getById_Should_Return_Correct_Name() {
        String endpoint = getProductsEndpoint() + "/" + PRODUCT_ID;
        String stringResponse = this.restTemplate.getForObject(endpoint, String.class);
        JSONObject jsonResponse = new JSONObject(stringResponse);

        assertThat(jsonResponse.get("name")).isEqualTo(PRODUCT_NAME);
    }

    /**
     * Testing POST request
     */
    @Test
    @Transactional
    public void addNew_Should_Return_Correct_Code() {
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> reqEntity = new HttpEntity<String>(POST_BODY, reqHeaders);
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(getProductsEndpoint(), reqEntity, String.class);

        assertThat(HttpStatus.CREATED).isEqualTo(responseEntity.getStatusCode());
    }

    /**
     * Testing PUT request on existing record
     */
    @Test
    @Transactional
    public void update_Should_Update_Existing_Record() {
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> reqEntity = new HttpEntity<String>(PUT_BODY, reqHeaders);
        ResponseEntity<?> responseEntity = restTemplate.exchange(getProductsEndpoint() + "/4", HttpMethod.PUT, reqEntity, Product.class);

        Product updatedProduct = (Product) responseEntity.getBody();
        assertThat(updatedProduct.getName()).isEqualTo("TestProduct_For_PUT_Request");
    }

    /**
     * Testing PUT request on id corresponds to no record
     */
    @Test
    @Transactional
    public void update_Should_Create_New_Record() {
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> reqEntity = new HttpEntity<String>(PUT_BODY, reqHeaders);
        ResponseEntity<?> responseEntity = restTemplate.exchange(getProductsEndpoint() + "/44", HttpMethod.PUT, reqEntity, String.class);

        assertThat(HttpStatus.CREATED).isEqualTo(responseEntity.getStatusCode());
    }

    /**
     * Testing DELETE request
     */
    @Test
    @Transactional
    public void delete_Should_Return_Correct_Code() {
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<?> responseEntity = restTemplate.exchange(getProductsEndpoint() + "/6", HttpMethod.DELETE, null, String.class);
        assertThat(HttpStatus.NO_CONTENT).isEqualTo(responseEntity.getStatusCode());
    }
}
