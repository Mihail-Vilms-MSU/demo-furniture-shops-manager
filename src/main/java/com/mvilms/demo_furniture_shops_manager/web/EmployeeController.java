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

@RestController
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeResourceAssembler assembler;

    // #TODO#
    // Implement "NOT_FOUND" Exception
    @GetMapping("/employees/{id}")
    public EmployeeResource getById(@PathVariable Long id) {
        return assembler.toResource(employeeService.getById(id));
    }

    @GetMapping("/employees")
    public Resources<EmployeeResource> getAll() {

        List<EmployeeResource> employeeResourcesList =
                assembler.toResources(employeeService.getAll());

        Resources<EmployeeResource> employeeResources =
                new Resources<EmployeeResource>(employeeResourcesList);

        employeeResources
                .add(linkTo(methodOn(EmployeeController.class).getAll()).withSelfRel());

        return employeeResources;
    }

    @PostMapping("/employees")
    public EmployeeResource addNew(@RequestBody Employee newEmployee) {
        return assembler.toResource(employeeService.save(newEmployee));
    }

    @PutMapping("/employees/{id}")
    public EmployeeResource update(@RequestBody Employee newEmployee, @PathVariable Long id) {
        Employee savedEmployee;

        try {
            Employee oldEmployee = employeeService.getById(id);

            if (newEmployee.getFirstName() != null)
                oldEmployee.setFirstName(newEmployee.getFirstName());
            if (newEmployee.getLastName() != null)
                oldEmployee.setLastName(newEmployee.getLastName());
            if (newEmployee.getPhone() != null)
                oldEmployee.setPhone(newEmployee.getPhone());
            if (newEmployee.getEmail() != null)
                oldEmployee.setEmail(newEmployee.getEmail());
            if (newEmployee.getShopId() != null)
                oldEmployee.setShopId(newEmployee.getShopId());
            if (newEmployee.getShopId() != null)
                oldEmployee.setShopId(newEmployee.getShopId());

            savedEmployee = employeeService.save(oldEmployee);
        } catch (ProductNotFoundException exception) { // haven't found existing product record
            savedEmployee = employeeService.save(newEmployee);
        }

        return assembler.toResource(savedEmployee);
    }
}
