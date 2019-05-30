package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.EmployeeRepository;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.resources.EmployeeResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    //////////////////////////////////////////////////////////////////////

    public Employee getById(String id) {
        return repository.getOne(id);
    }

    public Page getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public List<Employee> getByShopId(String shopId){
        return repository.findByShopId(shopId);
    }

    //////////////////////////////////////////////////////////////////////

    public Employee save(Employee newEmployee){
        return repository.save(newEmployee);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
