package com.mvilms.demo_furniture_shops_manager.exceptions;

public class ShortageOfProduct extends RuntimeException {
    public ShortageOfProduct(Long shopId, Long productId) {
        super("There is lack of product");
    }
}
