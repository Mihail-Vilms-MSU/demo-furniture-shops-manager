package com.mvilms.demo_furniture_shops_manager.service;


import com.mvilms.demo_furniture_shops_manager.data.EmployeeRepository;
import com.mvilms.demo_furniture_shops_manager.exceptions.EmployeeNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.ShopToProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getById(Long id) {
        return employeeRepository.getOne(id);
    }

    public Page<Employee> getEmployeesByShopId(Long shopId){
        return employeeRepository
                .findByShopId(shopId, PageRequest.of(0, 5, Sort.by("lastName").descending()));
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee save(Employee newEmployee){
        return employeeRepository.save(newEmployee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
