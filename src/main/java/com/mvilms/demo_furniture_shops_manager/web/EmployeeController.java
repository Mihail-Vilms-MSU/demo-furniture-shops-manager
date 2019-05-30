package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.resources.EmployeeResource;
import com.mvilms.demo_furniture_shops_manager.resources.EmployeeResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.EmployeeService;
import com.mvilms.demo_furniture_shops_manager.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

// http://localhost:8080/employees/search/findByShopId?shopId=1

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    private final EmployeeService service;
    private final ShopService shopService;
    private final EmployeeResourceAssembler assembler;

    public EmployeeController(EmployeeService service, ShopService shopService) {
        this.service = service;
        this.assembler = new EmployeeResourceAssembler();
        this.shopService = shopService;
    }

    //////////////////////////////////////////////////////////////////////////

    @GetMapping("/employees/{id}")
    public EmployeeResource getById(@PathVariable String id) {
        return assembler.toResource(service.getById(id));
    }

    @GetMapping("/employees")
    public PagedResources<EmployeeResource> getAll(Pageable pageable) {
        Page page = service.getAll(pageable);

        List<EmployeeResource> employeeListResources= (List) page.getContent()
                .stream()
                .map(employee -> assembler.toResource((Employee) employee))
                .collect(Collectors.toList());

        PagedResources.PageMetadata pageMetadata =
                new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());

        return new PagedResources<EmployeeResource>(employeeListResources, pageMetadata);
    }

    @GetMapping("shops/{shopId}/employees")
    public List<EmployeeResource> getByShopId(@PathVariable String shopId){
        return service.getByShopId(shopId).stream()
                .map(employee -> new EmployeeResource(employee, false))
                .collect(Collectors.toList());
    }

    //////////////////////////////////////////////////////////////////////////

    @PostMapping("/employees")
    public ResponseEntity<?> addNew(@RequestBody Employee newEmployee, @RequestParam String shopId) {
        newEmployee.setShop(shopService.getById(shopId));
        service.save(newEmployee);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/employees/{id}")
    public EmployeeResource update(@RequestBody Employee newEmployee, @PathVariable String id) {
        Employee savedEmployee;

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

        return assembler.toResource(savedEmployee);
    }

}
