package com.mvilms.demo_furniture_shops_manager.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee record with id: " + id);
    }
}
