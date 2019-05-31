package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value="employee", collectionRelation="employees")
public class EmployeeResource extends ResourceSupport {
    @Getter
    private String employeeId;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private String phone;
    @Getter
    private String email;
    @Getter
    private String role;
    @Getter
    private Shop shop;

    public EmployeeResource(Employee employee) {
        this.employeeId = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.phone = employee.getPhone();
        this.email = employee.getEmail();
        this.role = employee.getRole();
    }

    public EmployeeResource(Employee employee, boolean needShopInfo) {
        this(employee);
        if (needShopInfo) this.shop = employee.getShop();
    }

}
