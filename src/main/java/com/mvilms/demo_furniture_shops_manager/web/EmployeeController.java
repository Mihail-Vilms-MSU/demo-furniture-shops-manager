package com.mvilms.demo_furniture_shops_manager.web;

import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.resources.EmployeeResource;
import com.mvilms.demo_furniture_shops_manager.resources.EmployeeResourceAssembler;
import com.mvilms.demo_furniture_shops_manager.service.EmployeeService;
import com.mvilms.demo_furniture_shops_manager.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
public class EmployeeController {
    private final EmployeeService service;
    private final ShopService shopService;
    private final EmployeeResourceAssembler assembler;

    public EmployeeController(EmployeeService service, ShopService shopService) {
        this.service = service;
        this.assembler = new EmployeeResourceAssembler();
        this.shopService = shopService;
    }

    /**
     * Returns employee record
     *
     * @param id Employee record Id
     * @return Employee record
     */
    @GetMapping("/employees/{id}")
    public EmployeeResource getById(@PathVariable String id) {
        return assembler.toResource(service.getById(id));
    }


    /**
     * Takes no arguments or one argument - string in order to search records by multiple fields
     * In first case method returns all records in database in table "employees"
     * In second case method returns records that contains input value in one of theirs fields
     *
     * @param searchInput String to search
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    @GetMapping("/employees")
    public PagedResources<EmployeeResource> getAll(
            @RequestParam(value ="searchInput", required = false, defaultValue = "") String searchInput,
            Pageable pageable
    ) {
        Page page;
        if (searchInput.equals("")){
            page = service.getAll(pageable);
        } else {
            page = service.liveSearch(searchInput, pageable);
        }

        return PagedResourcesBuilder.<Employee, EmployeeResource>build(page, assembler);
    }


    /**
     * Returns search result by several fields
     *
     * @param firstName Search parameter for "first.name" column
     * @param lastName Search parameter for "last.name" column
     * @param shopId Search parameter for "shop.id" column
     * @param role Search parameter for "role" column
     *
     * @param pageable Pagination configuration parameters
     * @return Page with search results
     */
    @GetMapping("/employees/advancedSearch")
    public PagedResources<EmployeeResource> advancedSearch(
            @RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
            @RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
            @RequestParam(value = "shopId", required = false, defaultValue = "") String shopId,
            @RequestParam(value = "role", required = false, defaultValue = "") String role,
            Pageable pageable
    ) {
        Page page = service.advancedSearch(firstName, lastName, shopId, role, pageable);
        return PagedResourcesBuilder.<Employee, EmployeeResource>build(page, assembler);
    }


    /**
     * Creates new employee record
     *
     * @param newEmployee json in request body that consists all fields values of new record
     * @param shopId Id of shop record related to employee field
     * @return Employee record created after handling request
     */
    @PostMapping("/employees")
    public EmployeeResource addNew(@RequestBody Employee newEmployee, @RequestParam String shopId) {
        newEmployee.setShop(shopService.getById(shopId));
        Employee emp = service.save(newEmployee);

        return assembler.toResource(emp);
    }


    /**
     * Updates employee record that already exists it database
     *
     * @param newEmployee json with updated fields values
     * @param id employees record id we shall update
     * @param shopId Id of shop record related to employee field
     * @return Employee record updated after handling request
     */
    @PutMapping("/employees/{id}")
    public EmployeeResource update(
            @RequestBody Employee newEmployee,
            @PathVariable String id,
            @RequestParam(value ="shopId", required = false, defaultValue = "") String shopId
    ) {
        Employee savedEmployee;

        Employee oldEmployee = service.getById(id);

        if (newEmployee.getFirstName() != null)
            oldEmployee.setFirstName(newEmployee.getFirstName());
        if (newEmployee.getLastName() != null)
            oldEmployee.setLastName(newEmployee.getLastName());
        if (newEmployee.getRole() != null)
            oldEmployee.setLastName(newEmployee.getRole());
        if (newEmployee.getLastName() != null)
            oldEmployee.setLastName(newEmployee.getLastName());
        if (newEmployee.getPhone() != null)
            oldEmployee.setPhone(newEmployee.getPhone());
        if (newEmployee.getEmail() != null)
            oldEmployee.setEmail(newEmployee.getEmail());

        if (!shopId.equals(""))
            oldEmployee.setShop(shopService.getById(shopId));

        savedEmployee = service.save(oldEmployee);

        return assembler.toResource(savedEmployee);
    }


//    /**
//     *
//     * @param shopId
//     * @return
//     */
//    @GetMapping("shops/{shopId}/employees")
//    public List<EmployeeResource> getByShopId(@PathVariable String shopId){
//        return service.getByShopId(shopId).stream()
//                .map(employee -> new EmployeeResource(employee, false))
//                .collect(Collectors.toList());
//    }
}
