package com.mvilms.demo_furniture_shops_manager.service;

import com.mvilms.demo_furniture_shops_manager.data.EmployeeRepository;
import com.mvilms.demo_furniture_shops_manager.exceptions.EmployeeNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getById(Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
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
