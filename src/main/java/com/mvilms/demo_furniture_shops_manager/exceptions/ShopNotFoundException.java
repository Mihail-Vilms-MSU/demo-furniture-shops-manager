package com.mvilms.demo_furniture_shops_manager.exceptions;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(Long id) {
        super("Could not find shop record with id: " + id);
    }
}
