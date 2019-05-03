package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.exceptions.ProductNotFoundException;
import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.resources.EmployeeResource;
import com.mvilms.demo_furniture_shops_manager.resources.EmployeeResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

// http://localhost:8080/employees/search/findByShopId?shopId=1
@RestController
@Slf4j
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeResourceAssembler assembler;

    public EmployeeController(EmployeeService service, EmployeeResourceAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/{id}")
    public EmployeeResource getById(@PathVariable Long id) {
        return assembler.toResource(service.getById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees")
    public Resources<EmployeeResource> getAll() {
        List<EmployeeResource> employeeResourcesList =
                assembler.toResources(service.getAll());

        Resources<EmployeeResource> employeeResources =
                new Resources<EmployeeResource>(employeeResourcesList);

        employeeResources
                .add(linkTo(methodOn(EmployeeController.class).getAll()).withSelfRel());

        return employeeResources;
    }

    @PostMapping("/employees")
    public EmployeeResource addNew(@RequestBody Employee newEmployee) {
        return assembler.toResource(service.save(newEmployee));
    }

    @PutMapping("/employees/{id}")
    public EmployeeResource update(@RequestBody Employee newEmployee, @PathVariable Long id) {
        Employee savedEmployee;

        try {
            Employee oldEmployee = service.getById(id);

            if (newEmployee.getFirstName() != null)
                oldEmployee.setFirstName(newEmployee.getFirstName());
            if (newEmployee.getLastName() != null)
                oldEmployee.setLastName(newEmployee.getLastName());
            if (newEmployee.getPhone() != null)
                oldEmployee.setPhone(newEmployee.getPhone());
            if (newEmployee.getEmail() != null)
                oldEmployee.setEmail(newEmployee.getEmail());

            savedEmployee = service.save(oldEmployee);
        } catch (ProductNotFoundException exception) { // haven't found existing product record
            savedEmployee = service.save(newEmployee);
        }

        return assembler.toResource(savedEmployee);
    }
}
