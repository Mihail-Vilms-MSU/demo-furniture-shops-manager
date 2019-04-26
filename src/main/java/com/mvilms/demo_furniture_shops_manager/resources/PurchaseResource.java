package com.mvilms.demo_furniture_shops_manager.resources;

import com.mvilms.demo_furniture_shops_manager.model.Purchase;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.util.Date;

@Relation(value="purchase", collectionRelation="purchases")
public class PurchaseResource extends ResourceSupport {
    @Getter
    private Long employeeId;
    @Getter
    private Long shopId;
    @Getter
    private BigDecimal price;
    @Getter
    private Date createdAt;

    public PurchaseResource(Purchase purchase){
        this.employeeId = purchase.getEmployeeId();
        this.price = purchase.getTotalPrice();
        this.shopId = purchase.getShopId();
        this.createdAt = purchase.getRegisteredAt();
    }
}
