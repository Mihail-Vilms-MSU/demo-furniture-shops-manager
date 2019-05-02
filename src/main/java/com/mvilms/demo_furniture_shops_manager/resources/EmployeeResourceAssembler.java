package com.mvilms.demo_furniture_shops_manager.resources;


import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.web.EmployeeController;
import com.mvilms.demo_furniture_shops_manager.web.ShopController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class EmployeeResourceAssembler extends ResourceAssemblerSupport<Employee, EmployeeResource> {

    public EmployeeResourceAssembler() {
        super(EmployeeController.class, EmployeeResource.class);
    }

    @Override
    public EmployeeResource toResource(Employee employee) {
        EmployeeResource employeeResource = new EmployeeResource(employee);

        employeeResource.add(linkTo(methodOn(EmployeeController.class).getById(employee.getId())).withSelfRel());

        employeeResource.add(linkTo(methodOn(EmployeeController.class).getAll()).withRel("employees"));

        // employeeResource.add(linkTo(methodOn(ShopController.class).getById(employee.getShopId())).withRel("shop"));

        return employeeResource;
    }

}
