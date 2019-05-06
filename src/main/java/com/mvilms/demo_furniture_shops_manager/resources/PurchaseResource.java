package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.util.Date;

@Relation(value="purchase", collectionRelation="purchases")
public class PurchaseResource extends ResourceSupport {
    @Getter
    private Long purchaseId;
    @Getter
    private Employee employee;
    @Getter
    private Shop shop;
    @Getter
    private BigDecimal price;
    @Getter
    private Date createdAt;

    public PurchaseResource(Purchase purchase){
        this.purchaseId = purchase.getId();
        this.employee = purchase.getEmployee();
        this.shop = purchase.getShop();

        this.price = purchase.getTotalPrice();
        this.createdAt = purchase.getRegisteredAt();
    }
}
